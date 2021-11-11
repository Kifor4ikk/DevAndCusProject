package service;

import dao.RequestRepository;
import model.RequestModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RequestService implements RequestRepository {

    private final Connection connection;
    public RequestService(Connection connection) {
        this.connection = connection;
    }

    public Statement state() throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void addRequest(RequestModel request) throws SQLException {

    }

    @Override
    public void removeRequest(RequestModel request) throws SQLException {

    }

    @Override
    public RequestModel findById(long id) throws SQLException {
        return null;
    }

    @Override
    public RequestModel findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<RequestModel> getAll() throws SQLException {
        return null;
    }
}
