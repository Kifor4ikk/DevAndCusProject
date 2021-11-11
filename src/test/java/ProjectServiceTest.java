import configs.Database;
import entity.Project;
import entity.ProjectStatus;
import model.CustomerModel;
import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.ProjectService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public ProjectModel getProjectByName(String name) throws SQLException;
     */

    @Test
    public void createNewProject() throws SQLException {
        //String type, BigDecimal cost, java.sql.Date deadline, CustomerModel customer, List<String> tasks
        List<String> tasks = new ArrayList<>();
        tasks.add("Create new developers");
        tasks.add("Created DataBase");
        tasks.add("Pochesat jopu");

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



}
