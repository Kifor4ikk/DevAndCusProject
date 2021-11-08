package model;

import entity.Developer;
import entity.DeveloperStatus;
import entity.Qualities;

public class DeveloperModel {

    private long id;
    private String mainQualification;
    private String name;
    private DeveloperStatus status;
    private Qualities quality;

    public DeveloperModel(long id, String mainQualification, String name, DeveloperStatus status, Qualities quality) {
        this.id = id;
        this.mainQualification = mainQualification;
        this.name = name;
        this.status = status;
        this.quality = quality;
    }

    public DeveloperModel(Developer developer){
        this.id = developer.getId();
        this.mainQualification = developer.getMainQualification();
        this.name = developer.getName();
        this.status = developer.getStatus();
        this.quality = developer.getQuality();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainQualification() {
        return mainQualification;
    }

    public void setMainQualification(String mainQualification) {
        this.mainQualification = mainQualification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeveloperStatus getStatus() {
        return status;
    }

    public void setStatus(DeveloperStatus status) {
        this.status = status;
    }

    public Qualities getQuality() {
        return quality;
    }

    public void setQuality(Qualities quality) {
        this.quality = quality;
    }

    @Override
    public String toString(){

        return "\n#" + id + " " + name + "\n"
                + mainQualification + " | " + quality + "\n"
                + status + "\n";
    }
}
