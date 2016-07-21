package rest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by amcmanigal on 5/26/2016.
 */
public class MingleRequestBuilder {
    public enum QueryType{
        GET_EPIC,
        GET_STORY_CARDS
    }

    public HashMap<QueryType, String> apiSearches = new HashMap<QueryType, String>();

    private MingleUser user;
    private MingleProject project;
    private String baseURL;

    public MingleRequestBuilder(MingleUser user, MingleProject project){
        this.user = user;
        this.project = project;
        loadSupportedQueries();
        baseURL = assembleBaseURL();
    }

    private void loadSupportedQueries(){
        apiSearches.put(QueryType.GET_EPIC, "SELECT number, name, description, type WHERE Type = 'Epic' AND name = '%s'");
        apiSearches.put(QueryType.GET_STORY_CARDS, "SELECT number, name, description, type WHERE Type = 'Story' AND Epic = '%s'");
    }

    public HttpUriRequest getURI(QueryType query, String parentName) throws UnsupportedEncodingException{

        String withMethod = baseURL + "cards/execute_mql.xml?mql=";
        HttpGet get = new HttpGet(withMethod + URLEncoder.encode(String.format(apiSearches.get(query), parentName), "UTF-8"));
        get.addHeader("Authorization", "Basic " + createAuthHeaderString());
        return get;

    }

    private String createAuthHeaderString(){
        String authString = user.getUserID() + ":" + user.getPassword();
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        return new String(authEncBytes);
    }

    private String assembleBaseURL(){

        StringBuilder builder =  new StringBuilder();
        builder.append("https://");
        builder.append(project.getServerURL());
        builder.append(project.getProjectName() + "/");
        return builder.toString();
    }
}
