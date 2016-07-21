package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataobject.Card;
import org.junit.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import response.MingleResponseParser;
import rest.MingleProject;
import rest.MingleRequestSender;
import rest.MingleUser;
import rest.MingleRequestBuilder;

import javax.validation.constraints.AssertTrue;
import java.util.List;

/**
 * Created by amcmanigal on 5/26/2016.
 */
public class RetrievingCardsSteps {

    MingleProject project;
    MingleUser user;
    HttpUriRequest requestURI;
    HttpResponse response;

    //Background
    @Given("^a Mingle server address of (.*)$")
    public void a_Mingle_server_address_of(String url) throws Throwable {
        project = new MingleProject().withServerURL(url);
    }

    @And("^a Mingle project name of (.*)$")
    public void a_Mingle_project_name_of_core_search(String projectName) throws Throwable {
        project.withProjectName(projectName);
    }

    @And("^a user name of (.*)$")
    public void a_user_name_of_(String userName) throws Throwable {
      user = new MingleUser().withUserID(userName);
    }

    @And("^a password of (.*)$")
    public void a_password_of(String password) throws Throwable {
        user.withPassword(password);
    }

    //Scenario: Getting a single Epic card.
    @Given("^I want to get an Epic card with the name (.*)$")
    public void I_want_to_get_an_Epic_card_with_the_name(String epicName) throws Throwable {
        requestURI = new MingleRequestBuilder(user,project).getURI(
                MingleRequestBuilder.QueryType.GET_EPIC,
                epicName
        );
    }

    //Scenario: Getting the story cards that belong to the Update Mingle Through Tests epic
    @Given("^I want to retrieve all the cards belonging to the (.*) epic$")
    public void I_want_to_retrieve_all_the_cards_belonging_to_the_epic(String epicName) throws Throwable {
        requestURI = new MingleRequestBuilder(user,project).getURI(
                MingleRequestBuilder.QueryType.GET_STORY_CARDS,
                epicName
        );
    }

    @When("^I send the request to the Mingle server$")
    public void I_send_the_request_to_the_Mingle_server() throws Throwable {
        response = MingleRequestSender.getRequest(requestURI);
    }

    @Then("^I should receive a single card with the name (.*)$")
    public void I_should_receive_a_single_card_with_the_name(String epicName) throws Throwable {
        List<Card> cards = MingleResponseParser.createCards(response);
        Assert.assertTrue(cards.get(0).name().equals(epicName));

    }

    @Then("^I should receive the story (.*) in the results$")
    public void I_should_receive_the_story_in_the_results(String storyName) throws Throwable {
        List<Card> cards = MingleResponseParser.createCards(response);
        Assert.assertTrue(cards.size() >= 1);

    }

}
