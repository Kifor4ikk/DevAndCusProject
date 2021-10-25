package dao;

import entity.Developer;

public interface DeveloperRepository {

    public Developer newDeveloper();
    public void deleteDeveloper();
    public void setDeveloperStatus();
}
