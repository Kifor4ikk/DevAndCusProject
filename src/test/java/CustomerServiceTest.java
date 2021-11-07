import configs.Database;
import entity.Customer;
import entity.Project;
import org.junit.jupiter.api.Test;
import service.CustomerService;

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

    @org.junit.jupiter.api.Test
    public void test(){
        System.out.println(new java.util.Date());
    }
    @Test
    public void testCreateCustomer() throws SQLException {
        customerService.createCustomer("Oleg");
    }

    @Test
    public void testUpdateCustomer() throws SQLException {
        customerService.updateCustomer("Oleg","OlegEdited");
    }
    @Test
    public void testFindById() throws SQLException {
        System.out.println(customerService.getCustomerById(1));
    }

    @Test
    public void findByName() throws SQLException {
        System.out.println(customerService.getCustomerByName("Oleg"));
    }

    @Test
    public void findAll() throws SQLException {
        for(Customer customer : customerService.getAllCustomers()){
            System.out.println("# " + customer);
        }
    }

    @Test
    public void findProjects() throws SQLException{

        for (Project project: customerService.getCustomerProjects(1)){
            System.out.println("# -> " + project.toString());
        }
    }
}
