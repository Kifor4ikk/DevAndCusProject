package dao;

import model.RequestModel;

import java.sql.SQLException;
import java.util.List;

public interface RequestRepository {

    public void addRequest(RequestModel request) throws SQLException;
    public void removeRequest(RequestModel request) throws SQLException;
    public RequestModel findById(long id) throws SQLException;
    public RequestModel findByName(String name) throws SQLException;
    public List<RequestModel> getAll() throws SQLException;
}
