package response;

import dataobject.Card;
import extractors.MingleCardExtractor;
import org.apache.http.HttpResponse;
import java.io.*;
import java.util.List;

public class MingleResponseParser {

    public static List<Card> createCards(HttpResponse response) throws IOException{

        return MingleCardExtractor.processCardStream(response.getEntity().getContent());
    }

}
