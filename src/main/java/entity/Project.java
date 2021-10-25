package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

    private long id;
    private String name;
    private BigDecimal cost;
    private Date deadLine;
    private Customer customer;
    private ProjectStatus status;
    private List<Developer> listOfDevelopers;

    public Project(long id, String name, BigDecimal cost, Date deadline, Customer customer){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.deadLine = deadline;
        this.customer = customer;
        this.listOfDevelopers = new ArrayList<>();
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

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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
}
