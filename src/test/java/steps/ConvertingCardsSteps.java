package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataobject.Card;
import extractors.MingleCardExtractor;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ConvertingCardsSteps{

    ByteArrayInputStream xmlStream;
    List<Card> cards;

    @Given("^I have retrieved the following Epic card from Mingle:$")
    public void I_have_retrieved_the_Epic_card_from_Mingle(String xmlBody) throws Throwable {
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(xmlBody);
        xmlStream =  new ByteArrayInputStream(
                xmlStringBuilder.toString().getBytes("UTF-8"));
    }

    @When("^I send the cards to be converted$")
    public void I_send_the_card_to_be_converted() throws Throwable {
        cards = MingleCardExtractor.processCardStream(xmlStream);
    }


    @Then("^I should have (\\d+) cards after the conversion$")
    public void I_should_get_cards_back(int arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
