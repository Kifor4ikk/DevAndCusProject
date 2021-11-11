import configs.Database;
import entity.Customer;
import entity.Project;
import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.ProjectService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerServiceTest {

    private static CustomerService customerService = null;

    static{
        try {
            Connection connection = Database.connectWithDataBase();
            customerService = new CustomerService(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    public List<Project> getCustomerProjects(long id) throws SQLException;
     */

    @Test
    public void createCustomer() throws SQLException {
        customerService.createCustomer("AndrewICO");
    }

    @Test
    public void updateCustomer() throws SQLException {
        customerService.updateCustomer("AndrewICO", "AndrewSAN ICO");
    }

    @Test
    public void getAllCustomers() throws SQLException {
        for (Customer customer : customerService.getAllCustomers()){
            System.out.println(customer);
        }
    }

    @Test
    public void getCustomerById() throws SQLException{
        System.out.println(customerService.getCustomerById(1));
    }

    @Test
    public void getCustomerByName() throws SQLException{
        System.out.println(customerService.getCustomerByName("AndrewSAN ICO"));
    }

    @Test
    public void getCustomerProjects() throws SQLException{

        for(Project project : customerService.getCustomerProjects(1)){
            System.out.println(project);
        }
    }
}
