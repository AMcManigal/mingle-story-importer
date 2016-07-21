Feature: Retrieving Mingle cards
  As a programmer
  I want to retrieve Mingle cards
  So that I can easily create feature files

  Background:
    Given a Mingle server address of careerbuilder.mingle-api.thoughtworks.com/api/v2/projects/
    And a Mingle project name of core_search
    And a user name of apiadmin@careerbuilder.com
    And a password of api.cb5550

  Scenario: Getting an Epic card using an epic name
    Given I want to get an Epic card with the name Mingle Cucumber Story Creator
    When I send the request to the Mingle server
    Then I should receive a single card with the name Mingle Cucumber Story Creator

  Scenario: Getting the story cards that belong to an epic
    Given I want to retrieve all the cards belonging to the Mingle Cucumber Story Creator epic
    When I send the request to the Mingle server
    Then I should receive the story Retrieving Stories For An Epic in the results

