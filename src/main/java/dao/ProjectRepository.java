package dao;

import entity.Project;
import entity.ProjectStatus;
import model.ProjectModel;
import model.RequestModel;

import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {

    public void createNewProject(Project project) throws SQLException;
    public void setProjectStatus(long id, ProjectStatus status) throws SQLException;
    public ProjectModel getProjectById(long id) throws SQLException;
    public ProjectModel getProjectByType(String name) throws SQLException;
    public List<ProjectModel> getAllProjects() throws SQLException;
    public void createNewRequest(RequestModel requestModel) throws SQLException;
    public void declineRequest(long id) throws SQLException;
    public RequestModel getRequestById(long id) throws SQLException;
    public RequestModel getRequestByType(String type) throws SQLException;
    public List<RequestModel> getAllRequests() throws SQLException;
}
