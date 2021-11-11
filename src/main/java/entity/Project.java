package entity;

import model.CustomerModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private long id;
    private String type;
    private BigDecimal cost;
    private Date deadLine;
    private CustomerModel customer;
    private ProjectStatus status;
    private List<Developer> team;
    private List<String> tasks;

    public Project(){}

    public Project(String type, BigDecimal cost, java.sql.Date deadline, CustomerModel customer, List<String> tasks){
        this.type = type;
        this.cost = cost;
        this.deadLine = deadline;
        this.customer = customer;
        this.team = new ArrayList<>();
        this.tasks = tasks;
        status = ProjectStatus.ACTIVE;
    }

    public Project(long id, String type, BigDecimal cost, java.sql.Date deadline, CustomerModel customer,
                   ProjectStatus projectStatus, List<String> tasks){
        this(type,cost,deadline,customer,tasks);
        this.team = new ArrayList<>();
        status = projectStatus;
        this.id = id;
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

    public List<Developer> getTeam() {
        return team;
    }

    public void setTeam(List<Developer> team) {
        this.team = team;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString(){

        return "\n-------------------------------\n" +
                "#" + this.id + " " + this.type + "\n" +
                this.cost + " | " + deadLine + "\n" +
                "STATUS: " + status + "\n" +
                this.customer + "\n" + team + " \n"
                + tasks +
                "\n-------------------------------\n";
    }
}
