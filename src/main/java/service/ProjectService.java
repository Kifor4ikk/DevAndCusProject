package service;

import dao.ProjectRepository;
import entity.*;
import exception.NotFoundException;
import model.DeveloperModel;
import model.ProjectModel;
import model.RequestModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
    public void createNewProject(long requestId,Project project) throws SQLException {
        state().execute("UPDATE project_entity SET cost = " + project.getCost() +
                ",name = '" + project.getType() + "', deadline = '" + project.getDeadLine() +"', status = 'ACTIVE' WHERE id = " + requestId);
    }

    @Override
    public void setProjectStatus(long id, ProjectStatus status) throws SQLException {
        state().execute("update project_entity set status = '" + status + "' where id = " + id);
    }

    @Override
    public ProjectModel getProjectByType(String name) throws SQLException {
        ProjectModel project = new ProjectModel();
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity where name = '" + name +
                "' AND cost IS NOT NULL AND status IS NOT NULL and deadline IS NOT NULL")) {
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
        if (project.getId() == 0)
            throw new NotFoundException("Project with current name WAS NOT FOUND");

        return project;
    }

    @Override
    public ProjectModel getProjectById(long id) throws SQLException {
        ProjectModel project = new ProjectModel();

        try (ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity where id = '" + id +
                "' AND cost IS NOT NULL AND status IS NOT NULL and deadline IS NOT NULL")) {
            while (resultSet.next()) {
                project.setId(resultSet.getLong("id"));
                project.setCost(resultSet.getBigDecimal("cost"));
                project.setType(resultSet.getString("name"));
                project.setDeadLine(resultSet.getDate("deadline"));
                project.setStatus(ProjectStatus.valueOf(resultSet.getString("status")));
                project.setCustomer(customerService.getCustomerModelByProjectId(id));
                project.setTeam(getAllDevModelFromProject(1));
            }
            if (project.getId() == 0)
                throw new NotFoundException("Project with current ID not found");

        }
        return project;
    }

    @Override
    public List<ProjectModel> getAllProjects() throws SQLException {
        List<ProjectModel> list = new ArrayList<>();

        try (ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity WHERE " +
                "cost IS NOT NULL AND status IS NOT NULL and deadline IS NOT NULL")) {

            //long id, String type, BigDecimal cost, Date deadLine,
            //CustomerModel customer, ProjectStatus status, List<DeveloperModel> listOfDevs, List<String> tasks
            while (resultSet.next()) {
                list.add(
                        new ProjectModel(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getBigDecimal("cost"),
                                resultSet.getDate("deadline"),
                                customerService.getCustomerModelByProjectId(resultSet.getLong("id")),
                                ProjectStatus.valueOf(resultSet.getString("status")),
                                getAllDevModelFromProject(resultSet.getLong("id")),
                                Collections.singletonList(resultSet.getArray("tasks").toString())
                        )
                );
            }
        }
        return list;
    }

    public List<DeveloperModel> getAllDevModelFromProject(long projectId) throws SQLException {
        List<DeveloperModel> developerModelList = new ArrayList<>();
        try (ResultSet resultSet = state().executeQuery("select * from developer_projects INNER" +
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

    @Override
    public void createNewRequest(RequestModel requestModel) throws SQLException {

        state().execute("INSERT INTO project_entity (name , tasks) VALUES (" +
                "'" + requestModel.getType() + "', '" +
                requestModel.getTasks() + "')");
        long projectId = 0;
        try (ResultSet resultSet = state().executeQuery("select id from project_entity where name = '" + requestModel.getType()
                + "'")) {
            while (resultSet.next()) {
                projectId = resultSet.getLong("id");
            }
        }

        state().execute("INSERT INTO customer_projects (projectId, customerId) VALUES ("
                + projectId + ", " + requestModel.getCustomerId() + ")");

    }

    public void declineRequest(long id) throws SQLException {
        state().execute("UPDATE project_entity WHERE id = '" + id + "' AND cost is NULL AND" +
                " deadline is NULL AND status is NULL SET status = " + ProjectStatus.DECLINED);
    }

    public RequestModel getRequestById(long id) throws SQLException {

        RequestModel requestModel = new RequestModel();
        requestModel.setId(id);
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity WHERE id = '" + id + "' AND cost is NULL AND" +
                " deadline is NULL AND status is NULL");) {
            while (resultSet.next()) {
                requestModel.setCustomerId(customerService.getCustomerModelByProjectId(resultSet.getLong("id")).getId());
                requestModel.setType(resultSet.getString("name"));
                requestModel.setTasks(Collections.singletonList(resultSet.getArray("tasks").toString()));
            }
        }
        if (requestModel.getId() == 0) throw new NotFoundException("Request with current ID not FOUND");

        return requestModel;
    }

    public RequestModel getRequestByType(String type) throws SQLException {

        RequestModel requestModel = new RequestModel();
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity WHERE name = '"
                + type + "' AND cost is NULL AND" +
                " deadline is NULL AND status is NULL")) {
            while (resultSet.next()) {
                requestModel.setId(resultSet.getLong("id"));
                requestModel.setCustomerId(customerService.getCustomerModelByProjectId(resultSet.getLong("id")).getId());
                requestModel.setType(resultSet.getString("type"));
                requestModel.setTasks(Collections.singletonList(resultSet.getArray("tasks").toString()));
            }
        }

        return requestModel;
    }

    public List<RequestModel> getAllRequests() throws SQLException {
        List<RequestModel> list = new ArrayList<>();
        try (ResultSet resultSet = state().executeQuery("SELECT * FROM project_entity WHERE cost is NULL AND " +
                " deadline is NULL AND status is NULL")) {
            while (resultSet.next()) {
                list.add(
                        new RequestModel(
                                resultSet.getLong("id"),
                                customerService.getCustomerModelByProjectId(resultSet.getLong("id")).getId(),
                                resultSet.getString("name"),
                                Collections.singletonList(resultSet.getArray("tasks").toString())
                        )
                );
            }
        }
        return list;
    }
}
