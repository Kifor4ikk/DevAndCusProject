package dao;

import entity.Developer;
import entity.DeveloperStatus;
import entity.Qualities;

import java.sql.SQLException;
import java.util.List;

public interface DeveloperRepository {

    public void newDeveloper(Developer developer) throws SQLException;
    public void setDeveloperStatus(long id, DeveloperStatus status) throws SQLException;
    public void setDeveloperQuality(long id, Qualities quality) throws SQLException;
    public void addDeveloperToProject(long devId, long projectId) throws SQLException;
    public void removeDeveloperFromProject(long devId, long projectId);
    public List<Developer> getAllDevelopersWithStatus(DeveloperStatus status);

}
