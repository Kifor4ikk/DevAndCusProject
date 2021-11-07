package service;

import dao.DeveloperRepository;
import entity.Developer;
import entity.DeveloperStatus;
import entity.Qualities;

import java.sql.Connection;
import java.sql.Statement;

public class DeveloperService implements DeveloperRepository {

    private final Connection connection;

    public DeveloperService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void newDeveloper(Developer developer) {



    }

    @Override
    public void setDeveloperStatus(long id, DeveloperStatus status) {

    }

    @Override
    public void setDeveloperQuality(long id, Qualities quality) {

    }

    @Override
    public void addDeveloperToProject(long devId, long projectId) {

    }

    @Override
    public void removeDeveloperFromProject(long devId, long projectId) {

    }
}

