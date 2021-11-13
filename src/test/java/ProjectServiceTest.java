import configs.Database;
import entity.Project;
import entity.ProjectStatus;
import model.CustomerModel;
import model.ProjectModel;
import model.RequestModel;
import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.ProjectService;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectServiceTest {

    private static ProjectService projectService = null;
    private static CustomerService customerService = null;

    static{
        try {
            Connection connect = Database.connectWithDataBase();
            customerService = new CustomerService(connect);
            projectService = new ProjectService(connect, customerService);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    public void createNewProject(Project project) throws SQLException;
    public void setProjectStatus(long id, ProjectStatus status) throws SQLException;
    public ProjectModel getProjectById(long id) throws SQLException;
    public ProjectModel getProjectByType(String type) throws SQLException;
    public List<ProjectModel> getAllProjects() throws SQLException;
     */

    @Test
    public void createNewProject() throws SQLException {
        //String type, BigDecimal cost, java.sql.Date deadline, CustomerModel customer, List<String> tasks
        List<String> tasks = new ArrayList<>();
        tasks.add("Create new developers");
        tasks.add("Created DataBase");
        tasks.add("Create TESTS");
        projectService.createNewProject(
                new Project(
                        "PticeFabrica",
                        new BigDecimal("123"),
                        new Date(2022-1900,0,14),
                        customerService.getCustomerModelById(1),
                        tasks
                )
        );
    }

    @Test
    public void setProjectStatus() throws SQLException{
        projectService.setProjectStatus(1, ProjectStatus.CLOSED);
    }

    @Test
    public void getProjectById() throws SQLException{
        System.out.println(projectService.getProjectById(1));
        System.out.println(projectService.getProjectById(222));
    }

    @Test
    public void getProjectByType() throws SQLException{
        System.out.println(projectService.getProjectByType("TEST"));
    }

    @Test
    public void getAllProjects() throws SQLException {
        for(ProjectModel projectModel : projectService.getAllProjects()){
            System.out.println(projectModel);
        }
    }

    /*
    public void createNewRequest(RequestModel requestModel) throws SQLException;
    public void deleteRequest(long id) throws SQLException;
    public RequestModel getRequestById(long id) throws SQLException;
    public RequestModel getRequestByType(String type) throws SQLException;
    public List<RequestModel> getAllRequests() throws SQLException;
     */
    @Test
    public void createNewRequest() throws SQLException {
        List<String> tasks = new ArrayList<>();
        tasks.add("Create new developers");
        tasks.add("Created DataBase");
        tasks.add("CREATE TESTS");

        projectService.createNewRequest(new RequestModel(
                1,
                1,
                "Test request",
                tasks
        ));
    }

    @Test
    public void declineRequest() throws SQLException{
        projectService.declineRequest(2);
    }

    @Test
    public void getRequestById() throws SQLException{
        System.out.println(projectService.getRequestById(2));
    }

    @Test
    public void getRequestByType() throws SQLException{
        System.out.println(projectService.getRequestByType("TESTTEST"));
    }

    @Test
    public void getAllRequests() throws SQLException {
        for (RequestModel requestModel : projectService.getAllRequests()){
            System.out.println(requestModel);
        }
    }

}
