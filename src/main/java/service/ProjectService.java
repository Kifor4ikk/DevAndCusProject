package service;

import dao.ProjectRepository;
import entity.*;
import exception.NotFoundException;
import model.DeveloperModel;
import model.ProjectModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProjectService implements ProjectRepository {

    private final Connection connection;
    private final CustomerService customerService;
    public ProjectService(Connection connection, CustomerService customerService) {
        this.connection = connection;
        this.customerService = customerService;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void createNewProject(Project project) throws SQLException {

        state().execute("INSERT INTO project_entity (cost, name , deadline, status, tasks) VALUES " +
                "(" +
                project.getCost() +  ","
                + "'" + project.getType() + "',"
                + "'" + project.getDeadLine() + "',"
                + "'" + project.getStatus().name() + "',"
                + "'" + project.getTasks() + "')");

        long projectId = getProjectByName(project.getType()).getId();
        state().execute("INSERT INTO customer_projects (projectId, customerId) VALUES ("
                + projectId + ", " + project.getCustomer().getId() + ")");
    }

    @Override
    public void setProjectStatus(long id, ProjectStatus status) throws SQLException {
        state().execute("update project_entity set status = "+ status +" where id = " + id);
    }

    @Override
    public ProjectModel getProjectByName(String name) throws SQLException {
        ProjectModel project = new ProjectModel();
        try(ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity where name = '" + name + "'")) {
            while (resultSet.next()) {
                project.setId(resultSet.getLong("id"));
                project.setCost(resultSet.getBigDecimal("cost"));
                project.setType(resultSet.getString("name"));
                project.setDeadLine(resultSet.getDate("deadline"));
                project.setStatus(ProjectStatus.valueOf(resultSet.getString("status")));
                project.setCustomer(customerService.getCustomerModelById(project.getId()));
                project.setTeam(getAllDevModelFromProject(1));
            }
        }
        return project;
    }

    @Override
    public ProjectModel getProjectById(long id) throws SQLException {
        ProjectModel project = new ProjectModel();

        try(ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity where id = " + id)) {
            while (resultSet.next()) {
                project.setId(resultSet.getLong("id"));
                project.setCost(resultSet.getBigDecimal("cost"));
                project.setType(resultSet.getString("name"));
                project.setDeadLine(resultSet.getDate("deadline"));
                project.setStatus(ProjectStatus.valueOf(resultSet.getString("status")));
                project.setCustomer(customerService.getCustomerModelById(id));
                project.setTeam(getAllDevModelFromProject(1));
            }
            if(project.getId() == 0){
                throw new NotFoundException("Project with current ID not found");
            }
        }
        return project;
    }


    public List<DeveloperModel> getAllDevModelFromProject(long projectId) throws SQLException{
        List<DeveloperModel> developerModelList = new ArrayList<>();
        try(ResultSet resultSet = state().executeQuery("select * from developer_projects INNER" +
                " JOIN developer_entity ON projectId = " + projectId +
                         " where developer_entity.id = developerId "
        )) {
            while (resultSet.next()) {
                developerModelList.add(new DeveloperModel(
                        resultSet.getLong("id"),
                        resultSet.getString("mainqualification"),
                        resultSet.getString("name"),
                        DeveloperStatus.valueOf(resultSet.getString("status")),
                        Qualities.valueOf(resultSet.getString("quality"))
                ));
            }
        }
        return developerModelList;
    }
}
