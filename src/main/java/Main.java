import configs.Database;
import entity.Developer;
import service.CustomerService;
import service.DeveloperService;
import service.ProjectService;
import ui.UI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = Database.connectWithDataBase();
        CustomerService customerService = new CustomerService(connection);
        ProjectService projectService = new ProjectService(connection,customerService);
        DeveloperService developerService = new DeveloperService(connection,customerService,projectService);
        UI ui = new UI("",customerService,developerService,projectService);
        ui.start();
    }
}
