package model;

import entity.Project;

import java.util.List;

public class RequestModel {

    private long customerId;
    private String type;
    private List<String> tasks;
    private int countOfTeam;

    public RequestModel(long customerId, String type, List<String> tasks, int countOfTeam) {
        this.customerId = customerId;
        this.type = type;
        this.tasks = tasks;
        this.countOfTeam = countOfTeam;
    }
}
