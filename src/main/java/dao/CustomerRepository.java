package dao;

import entity.Customer;
import entity.Project;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface CustomerRepository {

    public void createCustomer(String name) throws SQLException;
    public void updateCustomer(String name, String newName) throws SQLException;
    public List<Customer> getAllCustomers() throws SQLException;
    public Customer getCustomerById(long id) throws SQLException;
    public Customer getCustomerByName(String name) throws SQLException;
    public List<Project> getCustomerProjects(long id) throws SQLException;

}
