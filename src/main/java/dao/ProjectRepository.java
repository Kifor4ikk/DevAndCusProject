package dao;

import entity.Project;
import entity.ProjectStatus;
import model.ProjectModel;

import java.sql.SQLException;

public interface ProjectRepository {

    public void createNewProject(Project project) throws SQLException;
    public void setProjectStatus(long id, ProjectStatus status) throws SQLException;
    public ProjectModel getProjectById(long id) throws SQLException;
    public ProjectModel getProjectByName(String name) throws SQLException;
}
