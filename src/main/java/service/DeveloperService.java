package service;

import dao.DeveloperRepository;
import entity.*;
import model.ProjectModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeveloperService implements DeveloperRepository {

    private final Connection connection;
    private final CustomerService customerService;
    private final ProjectService projectService;

    public DeveloperService(Connection connection, CustomerService customerService, ProjectService projectService) {
        this.connection = connection;
        this.customerService = customerService;
        this.projectService = projectService;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public Developer getDeveloperById(long id) throws SQLException {
        Developer developer = null;
        try (ResultSet rs = state().executeQuery("SELECT * FROM developer_entity WHERE id = '" + id + "'")){
        //long id, String mainQualification, String name, Qualities quality, DeveloperStatus status, List<ProjectModel> list
            while(rs.next()){
               developer = new Developer(
                       id,
                       rs.getString("mainqualification"),
                       rs.getString("name"),
                       Qualities.valueOf(rs.getString("quality")),
                       DeveloperStatus.valueOf(rs.getString("status")),
                       getAllProjectsModelForDev(id)
               );
            }
        }
        return developer;
    }

    @Override
    public void newDeveloper(Developer developer) throws SQLException {
        state().execute("INSERT INTO developer_entity (mainqualification,name,status,quality) VALUES ('" +
                developer.getMainQualification() + "', '" +
                developer.getName() + "', '" +
                developer.getStatus().toString() + "', '" +
                developer.getQuality().toString() +
                 "')");
    }

    @Override
    public void setDeveloperStatus(long id, DeveloperStatus status) throws SQLException {
        state().execute("UPDATE developer_entity SET status = '" + status.toString() + "' WHERE id = '" + id + "'");
    }

    @Override
    public void setDeveloperQuality(long id, Qualities quality) throws SQLException {
        state().execute("UPDATE developer_entity SET quality = '" + quality.toString() + "' WHERE id = '" + id + "'");
    }

    @Override
    public void addDeveloperToProject(long devId, long projectId) throws SQLException {
        state().execute("INSERT INTO developer_projects (developerid, projectid) VALUES ('" + devId + "', '" + projectId + "')");
    }

    @Override
    public void removeDeveloperFromProject(long devId, long projectId) throws SQLException {
        state().execute("delete from developer_projects where developerid = '" + devId + "' and projectid = '" + projectId + "'");
    }

    @Override
    public List<Developer> getAllDevelopersWithStatus(DeveloperStatus status) throws SQLException {
        List<Developer> list = new ArrayList<>();
        //long id, String mainQualification, String name, Qualities quality, DeveloperStatus status, List<Project> list
        try (ResultSet rs = state().executeQuery("select * from developer_entity where status = '" + status + "'")) {
            while (rs.next()) {
                list.add(
                        new Developer(
                                rs.getLong("id"),
                                rs.getString("mainqualification"),
                                rs.getString("name"),
                                Qualities.valueOf(rs.getString("quality")),
                                DeveloperStatus.valueOf(rs.getString("status")),
                                getAllProjectsModelForDev(rs.getLong("id"))
                        )
                );
            }
            return list;
        }
    }


    public List<Developer> getAllDevelopers() throws SQLException {
        List<Developer> list = new ArrayList<>();
        //long id, String mainQualification, String name, Qualities quality, DeveloperStatus status, List<Project> list
        try (ResultSet rs = state().executeQuery("select * from developer_entity")) {
            while (rs.next()) {
                list.add(
                        new Developer(
                                rs.getLong("id"),
                                rs.getString("mainqualification"),
                                rs.getString("name"),
                                Qualities.valueOf(rs.getString("quality")),
                                DeveloperStatus.valueOf(rs.getString("status")),
                                getAllProjectsModelForDev(rs.getLong("id"))
                        )
                );
            }
            return list;
        }
    }

    public List<ProjectModel> getAllProjectsModelForDev(long devid) throws SQLException{
        List<ProjectModel> list = new ArrayList<>();
        try(ResultSet resultSet = state().executeQuery("select * from developer_projects INNER JOIN " +
                "project_entity ON developerId = "+ devid +" where developerId = '" + devid + "'")) {
            while (resultSet.next()) {
                    list.add(
                            new ProjectModel(
                                    resultSet.getLong("id"),
                                    resultSet.getString("name"),
                                    resultSet.getBigDecimal("cost"),
                                    resultSet.getDate("deadLine"),
                                    customerService.getCustomerModelByProjectId(resultSet.getLong("id")),
                                    ProjectStatus.valueOf(resultSet.getString("status")),
                                    projectService.getAllDevModelFromProject(resultSet.getLong("id"))
                            )
                    );
            }
        }
        return list;
    }
}

