package entity;

public class Developer {

    private long id;
    private String mainQualification;
    private String name;
    private Status status;
    private Qualities quality;
    private Project project;

    private boolean alreadyInProject;

    public Developer(long id, String mainQualification, String name, Qualities quality){
        this.id = id;
        this.mainQualification = mainQualification;
        this.name = name;
        this.quality = quality;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Qualities getQuality() {
        return quality;
    }

    public void setQuality(Qualities quality) {
        this.quality = quality;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isAlreadyInProject() {
        return alreadyInProject;
    }

    public void setAlreadyInProject(boolean alreadyInProject) {
        this.alreadyInProject = alreadyInProject;
    }
}
