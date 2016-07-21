package rest;

public class MingleProject {

    private String serverURL = "";
    private String projectName = "";

    public MingleProject withServerURL(String url){
        this.serverURL = url;
        return this;
    }

    public MingleProject withProjectName(String projectName){
        this.projectName = projectName;
        return this;
    }

    public String getServerURL() { return this.serverURL;}
    public String getProjectName() {return this.projectName;}

}
