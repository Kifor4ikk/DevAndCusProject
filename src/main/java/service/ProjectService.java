package service;

import dao.ProjectRepository;
import entity.Project;
import entity.ProjectStatus;
import exception.NotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProjectService implements ProjectRepository {

    private final Connection connection;
    private final CustomerService customerService;
    public ProjectService(Connection connection, CustomerService customerService) {
        this.connection = connection;
        this.customerService = customerService;
    }

    @Override
    public void createNewProject(Project project) throws SQLException {

        connection.createStatement().execute("INSERT INTO project_entity (cost, name , deadline, status) VALUES " +
                "(" +
                project.getCost() +  ","
                + "'" + project.getName() + "',"
                + "'" + project.getDeadLine() + "',"
                + "'" + project.getStatus().name() + "');");

        long projectId = getProjectByName(project.getName()).getId();
        connection.createStatement().execute("INSERT INTO customer_projects (projectId, customerId) VALUES ("
                + projectId + ", " + project.getCustomer().getId() + ")");
    }

    @Override
    public void closeProject(long id, ProjectStatus status) throws SQLException {

        connection.createStatement().execute("update project_entity set status = "+ status +" where id = " + id);
    }

    @Override
    public void addDevelopersToProject() throws SQLException {

    }

    @Override
    public Project getProjectByName(String name) throws SQLException {
        Project project = new Project();
        try(ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM project_entity where name = '" + name + "'")) {
            while (resultSet.next()) {
                project.setId(resultSet.getLong("id"));
                project.setCost(resultSet.getBigDecimal("cost"));
                project.setName(resultSet.getString("name"));
                project.setDeadLine(resultSet.getDate("deadline"));
                project.setStatus(ProjectStatus.valueOf(resultSet.getString("status")));
            }
        }
        return project;
    }

    @Override
    public Project getProjectById(long id) throws SQLException {
        Project project = new Project();

        try(ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM project_entity where id = " + id)) {
            while (resultSet.next()) {
                project.setId(resultSet.getLong("id"));
                project.setCost(resultSet.getBigDecimal("cost"));
                project.setName(resultSet.getString("name"));
                project.setDeadLine(resultSet.getDate("deadline"));
                project.setStatus(ProjectStatus.valueOf(resultSet.getString("status")));
                project.setCustomer(customerService.getCustomerModelById(id));
            }
            if(project.getId() == 0){
                throw new NotFoundException("Project with current ID not found");
            }
        }
        return project;
    }

}
