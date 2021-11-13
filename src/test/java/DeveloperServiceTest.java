import configs.Database;
import entity.Developer;
import entity.DeveloperStatus;
import entity.Qualities;
import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.DeveloperService;
import service.ProjectService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class DeveloperServiceTest {


    private static DeveloperService developerService = null;
    private static ProjectService projectService = null;

    static{
        try {
            Connection connection = Database.connectWithDataBase();
            CustomerService customerService = new CustomerService(connection);
            projectService = new ProjectService(connection,customerService);
            developerService = new DeveloperService(connection,customerService,projectService);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newDeveloper() throws SQLException {
        developerService.newDeveloper(
                (new Developer(
                        "C++",
                        "Miron Queue",
                        Qualities.STUDENT
                )
        ));
    }

    @Test
    public void getDeveloperById() throws SQLException {
        System.out.println(developerService.getDeveloperById(1));
    }

    @Test
    public void setDeveloperStatus() throws SQLException {
        developerService.setDeveloperStatus(1,DeveloperStatus.DISMISSED);
    }

    @Test
    public void setDeveloperQuality() throws SQLException {
        developerService.setDeveloperQuality(1, Qualities.SENIOR);
    }

    @Test
    public void addDeveloperToProject() throws SQLException {
        developerService.addDeveloperToProject(2,1);
    }

    @Test
    public void removeDeveloperFromProject() throws SQLException {
        developerService.removeDeveloperFromProject(1,1);
    }

    @Test
    public void getAllDevelopersWithStatus() throws SQLException{
        for(Developer developer :  developerService.getAllDevelopersWithStatus(DeveloperStatus.ACTIVE)){
            System.out.println(developer);
        }
        for(Developer developer :  developerService.getAllDevelopersWithStatus(DeveloperStatus.DISMISSED)){
            System.out.println(developer);
        }
    }

    @Test
    public void getAllDevs() throws SQLException{
        for (Developer developer : developerService.getAllDevelopers()){
            System.out.println(developer.toString());
        }
    }
}
