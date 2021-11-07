package entity;

import model.CustomerModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private long id;
    private String name;
    private BigDecimal cost;
    private Date deadLine;
    private CustomerModel customer;
    private ProjectStatus status;
    private List<Developer> listOfDevelopers;

    public Project(){}

    public Project(String name, BigDecimal cost, java.sql.Date deadline, CustomerModel customer){
        this.name = name;
        this.cost = cost;
        this.deadLine = deadline;
        this.customer = customer;
        this.listOfDevelopers = new ArrayList<>();
        status = ProjectStatus.ACTIVE;
    }

    public Project(long id, String name, BigDecimal cost, java.sql.Date deadline, CustomerModel customer, ProjectStatus projectStatus){
        this(name,cost,deadline,customer);
        this.listOfDevelopers = new ArrayList<>();
        status = projectStatus;
        this.id = id;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public java.sql.Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public List<Developer> getListOfDevelopers() {
        return listOfDevelopers;
    }

    public void setListOfDevelopers(List<Developer> listOfDevelopers) {
        this.listOfDevelopers = listOfDevelopers;
    }

    @Override
    public String toString(){

        return "\n-------------------------------\n" +
                "#" + this.id + " " + this.name + "\n" +
                this.cost + " | " + deadLine + "\n" +
                "STATUS: " + status + "\n" +
                this.customer + "\n" + listOfDevelopers +
                "\n-------------------------------\n";
    }
}
