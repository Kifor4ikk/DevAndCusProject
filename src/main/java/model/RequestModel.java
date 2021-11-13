package model;

import java.util.ArrayList;
import java.util.List;

public class RequestModel {

    private long id;
    private long customerId;
    private String type;
    private List<String> tasks = new ArrayList<>();

    public RequestModel() { }

    public RequestModel(long id, long customerId, String type, List<String> tasks) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.tasks = tasks;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString(){

        return "\n--------REQUEST PROJECT------\n" +
                "#" +  this.type + "\n" +
                "Customer id: " + customerId +  "\n" +
                "Tasks list: \n" + tasks +
                "\n----------------------------\n";
    }

}
