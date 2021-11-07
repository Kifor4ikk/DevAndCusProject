package dao;

import entity.Developer;
import entity.DeveloperStatus;
import entity.Qualities;

public interface DeveloperRepository {

    public void newDeveloper(Developer developer);
    public void setDeveloperStatus(long id, DeveloperStatus status);
    public void setDeveloperQuality(long id, Qualities quality);
    public void addDeveloperToProject(long devId, long projectId);
    public void removeDeveloperFromProject(long devId, long projectId);

}
