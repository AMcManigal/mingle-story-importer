package rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import response.MingleResponseParser;

import java.io.IOException;

public class MingleRequestSender {

    private MingleResponseParser parser;

    public static HttpResponse getRequest(HttpUriRequest request) throws IOException{
        CloseableHttpClient client = HttpClients.custom().build();
        return client.execute(request);

    }
}
