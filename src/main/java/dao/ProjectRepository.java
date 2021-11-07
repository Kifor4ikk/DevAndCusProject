package dao;

import entity.Project;
import entity.ProjectStatus;

import java.sql.SQLException;

public interface ProjectRepository {

    public void createNewProject(Project project) throws SQLException;
    public void setProjectStatus(long id, ProjectStatus status) throws SQLException;
    public Project getProjectById(long id) throws SQLException;
    public Project getProjectByName(String name) throws SQLException;
}
