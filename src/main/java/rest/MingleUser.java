package rest;

/**
 * Created by amcmanigal on 5/26/2016.
 */
public class MingleUser {

    private String userID = "";
    private String password = "";

    public MingleUser withUserID(String userID){
        this.userID = userID;
        return this;
    }

    public MingleUser withPassword(String password){
        this.password = password;
        return this;
    }

    public String getUserID(){
        return userID;
    }

    public String getPassword(){
        return password;
    }
}
