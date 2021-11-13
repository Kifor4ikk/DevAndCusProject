package model;

import entity.ProjectStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ProjectModel {
    private long id;
    private String type;
    private BigDecimal cost;
    private Date deadLine;
    private CustomerModel customer;
    private ProjectStatus status;
    private List<DeveloperModel> team;
    private List<String> tasks;


    public ProjectModel() { }

    public ProjectModel(long id, String type, BigDecimal cost, Date deadLine,
                        CustomerModel customer, ProjectStatus status, List<DeveloperModel> listOfDevs) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.deadLine = deadLine;
        this.customer = customer;
        this.status = status;
        this.setTeam(listOfDevs);
    }

    public ProjectModel(long id, String type, BigDecimal cost, Date deadLine,
                        CustomerModel customer, ProjectStatus status, List<DeveloperModel> listOfDevs, List<String> tasks) {
        this(id,type,cost,deadLine,customer,status,listOfDevs);
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getDeadLine() {
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

    public List<DeveloperModel> getTeam() {
        return team;
    }

    public void setTeam(List<DeveloperModel> team) {
        this.team = team;
    }

    @Override
    public String toString(){

        return "\n----------PROJECT---------\n" +
                "#" + this.id + " " + this.type + "\n" +
                this.cost + " | " + deadLine + "\n" +
                "STATUS: " + status + "\n" +
                this.customer + "\n" + team +
                "\n-------------------------------\n";
    }
}
