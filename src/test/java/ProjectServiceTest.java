import configs.Database;
import entity.Project;
import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.ProjectService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

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

    @Test
    public void createProjectTest() throws SQLException {

        projectService.createNewProject(
                new Project(
                        "Test2",
                        new BigDecimal("123"),
                        new Date(2022-1900,0,14),
                        customerService.getCustomerModelById(1)
        ));
    }

    @Test
    public void getProjectById() throws SQLException{
        System.out.println(projectService.getProjectById(1));
    }

    @Test
    public void getAllDevs() throws SQLException {
        System.out.println(projectService.getAllDevModelFromProject(1));
    }

}
