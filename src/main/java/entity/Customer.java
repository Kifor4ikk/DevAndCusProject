package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Customer {

    private long id;
    private String name;
    private List<Project> projectList = new ArrayList<>();

    public Customer(){}

    public Customer(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public String toString(){

        return  "#" + this.id + " | " + this.name + " |\n|PROJECTS| \n" +
                projectList.stream().map(project -> project.getType()).collect(Collectors.toList());
    }
}

