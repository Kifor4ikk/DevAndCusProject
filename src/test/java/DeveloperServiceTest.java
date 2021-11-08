import configs.Database;
import entity.Developer;
import entity.DeveloperStatus;
import entity.Qualities;
import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.DeveloperService;

import java.sql.Connection;
import java.sql.SQLException;

public class DeveloperServiceTest {


    private static DeveloperService developerService = null;

    static{
        try {
            Connection connection = Database.connectWithDataBase();
            developerService = new DeveloperService(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTest() throws SQLException {

        developerService.newDeveloper(new Developer(
                "JAVA",
                "Oleg Tabkov",
                Qualities.MIDDLE
        ));
    }

    @Test
    public void setStatus() throws SQLException {
        developerService.setDeveloperStatus(1, DeveloperStatus.DISMISSED);
    }

    @Test
    public void setQuality() throws SQLException {
        developerService.setDeveloperQuality(1, Qualities.SENIOR);
    }

    @Test
    public void addDevToPro() throws SQLException{
        developerService.addDeveloperToProject(1,1);
    }




}
