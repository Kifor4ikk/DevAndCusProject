package entity;

import model.CustomerModel;
import model.RequestModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private long id;
    private BigDecimal cost;
    private Date deadLine;
    private CustomerModel customer;
    private ProjectStatus status;
    private List<Developer> team;
    private RequestModel project;

    public Project(){}

    public Project(RequestModel requestModel){
        id = requestModel.getId();
        this.project = requestModel;
    }

    public Project(String type, BigDecimal cost, java.sql.Date deadline, CustomerModel customer, List<String> tasks){
        this.project.setType(type);
        this.cost = cost;
        this.deadLine = deadline;
        this.customer = customer;
        this.team = new ArrayList<>();
        this.project.setTasks(tasks);
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
        return this.project.getType();
    }

    public void setType(String type) {
        this.project.setType(type);
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
        return this.project.getTasks();
    }

    public void setTasks(List<String> tasks) {
        this.project.setTasks(tasks);
    }

    @Override
    public String toString(){

        return "\n-------------------------------\n" +
                "#" + this.id + " " + this.getType() + "\n" +
                this.cost + " | " + deadLine + "\n" +
                "STATUS: " + status + "\n" +
                this.customer + "\n" + team + " \n"
                + this.getTasks() +
                "\n-------------------------------\n";
    }
}
