package extractors;

import dataobject.Card;
import dataobject.CardType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class MingleCardExtractor {

    public static LinkedList<Card> processCardStream(InputStream stream) throws IOException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        LinkedList<Card> cards = new LinkedList<>();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(stream);
            cards = MingleCardExtractor.extractCards(document);

        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        return cards;
    }
    public static LinkedList<Card> extractCards(Document document){

        LinkedList<Card> cards = new LinkedList<Card>();
        NodeList cardNodes = document.getElementsByTagName("result");
        for(int i = 0; i < cardNodes.getLength(); i++){
            Element e = (Element)cardNodes.item(i);
            cards.add(new Card()
                            .withCardNumber(Integer.parseInt(e.getElementsByTagName("number").item(0).getTextContent()))
                            .withName(e.getElementsByTagName("name").item(0).getTextContent())
                            .ofType(determineType(e.getElementsByTagName("type").item(0).getTextContent()))
                            .withDescription(e.getElementsByTagName("description").item(0).getTextContent())
            );
        }
        return cards;
    }

    private static dataobject.CardType determineType(String type){
        switch (type.toLowerCase()){
            case "epic":
                return CardType.Epic;
            case "story":
                return CardType.Story;
            default:
                return CardType.Unspecified;
        }
    }
}
