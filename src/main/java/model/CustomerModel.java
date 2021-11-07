package model;

import entity.Customer;

public class CustomerModel {

    private long id;
    private String name;

    public CustomerModel() { }
    public CustomerModel(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
    }
    public CustomerModel(long id, String name) {
        this.id = id;
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

    @Override
    public String toString(){
        return "#" + id + "| " + name;
    }
}
