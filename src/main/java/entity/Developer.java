package entity;

import model.ProjectModel;

import java.util.ArrayList;
import java.util.List;

public class Developer {

    private long id;
    private String mainQualification;
    private String name;
    private DeveloperStatus status;
    private Qualities quality;
    private List<ProjectModel> projects = new ArrayList<>();;

    public Developer(String mainQualification, String name, Qualities quality){
        this.mainQualification = mainQualification;
        this.name = name;
        this.quality = quality;
        status = DeveloperStatus.ACTIVE;
    }

    public Developer(long id, String mainQualification, String name, Qualities quality, DeveloperStatus status, List<ProjectModel> list){
        this(mainQualification,name,quality);
        this.status = status;
        this.id = id;
        this.projects = list;
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

    public List<ProjectModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectModel> projects) {
        this.projects = projects;
    }

    @Override
    public String toString(){
        return "\n#" + id + " " + name + "\n"
                + mainQualification + " | " + quality + "\n"
                + status + "\n" + "|PROJECTS|\n" +
                projects;
    }
}
