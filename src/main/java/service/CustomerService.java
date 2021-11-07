package service;

import dao.CustomerRepository;
import entity.Customer;
import entity.Project;
import entity.ProjectStatus;
import exception.NotFoundException;
import model.CustomerModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerRepository {

    private final Connection connection;

    public CustomerService(Connection connection) {
        this.connection = connection;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
    }
    @Override
    public void createCustomer(String name) throws SQLException {
        connection.createStatement().execute("INSERT INTO customer_entity (name) VALUES ('" + name + "')");
    }

    @Override
    public void updateCustomer(String name, String newName) throws SQLException {
        connection.createStatement().execute("UPDATE customer_entity " +
                "SET name = '" + newName + "'" +
                "WHERE name = '" + name + "'");
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new ArrayList<>();
        try(ResultSet resultSet = state().executeQuery("SELECT * FROM customer_entity")) {
            Customer customer;
            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setProjectList(getCustomerProjects(customer.getId()));
                list.add(customer);
            }
        }
        return list;
    }

    @Override
    public Customer getCustomerById(long id) throws SQLException {
        Customer customer = new Customer();
        try(ResultSet resultSet = state().executeQuery("SELECT * FROM customer_entity WHERE id = " + id);){
            while (resultSet.next()){
                customer.setId(id);
                customer.setName(resultSet.getString("name"));
                customer.setProjectList(getCustomerProjects(id));
            }
            if(customer.getId() == 0){
                throw new NotFoundException("Customer with current ID not found!");
            }
            return customer;
        }
    }

    @Override
    public Customer getCustomerByName(String name) throws SQLException {
        Customer customer = new Customer();

        try(ResultSet resultSet = state().executeQuery("SELECT * FROM customer_entity WHERE name = '" + name + "'")) {

            while (resultSet.next()) {
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setProjectList(getCustomerProjects(customer.getId()));
            }
            if (customer.getId() == 0) {
                throw new NotFoundException("Customer with current ID not found!");
            }
        }
        return customer;
    }

    @Override
    public List<Project> getCustomerProjects(long id) throws SQLException {

        List<Project> projectList = new ArrayList<>();
        try(ResultSet resultSet = state().executeQuery("select * from customer_projects INNER JOIN project_entity ON customerId = " + id + " where project_entity.id = projectId");){
            while(resultSet.next()){
                projectList.add(
                        new Project(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getBigDecimal("cost"),
                                resultSet.getDate("deadLine"),
                                getCustomerModelById(id),
                                ProjectStatus.valueOf(resultSet.getString("status"))
                        )
                );

            }
        }
        return projectList;
    }

    public CustomerModel getCustomerModelById(long id) throws SQLException {
        CustomerModel customer = new CustomerModel();
        try(ResultSet resultSet = state().executeQuery("SELECT * FROM customer_entity WHERE id = " + id);){
            while (resultSet.next()){
                customer.setId(id);
                customer.setName(resultSet.getString("name"));
            }
            if(customer.getId() == 0){
                throw new NotFoundException("Customer with current ID not found!");
            }
            return customer;
        }
    }
}
