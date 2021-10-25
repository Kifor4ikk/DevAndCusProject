package dao;

import entity.Project;

public interface ProjectRepository {

    public Project createNewProject();
    public Project updateProject();
    public void closeProject();
}
