package service;

import dao.DeveloperRepository;
import entity.Developer;
import entity.DeveloperStatus;
import entity.Qualities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DeveloperService implements DeveloperRepository {

    private final Connection connection;

    public DeveloperService(Connection connection) {
        this.connection = connection;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
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
        state().execute("UPDATE developer_entity SET status = '" + status.toString() + "' WHERE id = " + id);
    }

    @Override
    public void setDeveloperQuality(long id, Qualities quality) throws SQLException {
        state().execute("UPDATE developer_entity SET quality = '" + quality.toString() + "' WHERE id = " + id);
    }

    @Override
    public void addDeveloperToProject(long devId, long projectId) throws SQLException {
        state().execute("INSERT INTO developer_projects (developerid, projectid) VALUES (" + devId + ", " + projectId + ")");
    }

    @Override
    public void removeDeveloperFromProject(long devId, long projectId) {

    }

    @Override
    public List<Developer> getAllDevelopersWithStatus(DeveloperStatus status) {
        return null;
    }
}

