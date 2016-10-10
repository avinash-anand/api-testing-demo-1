@wip

Feature: This feature will test the Long GET request & response of a REST API

  Scenario: Verify the long get request & response , send & received by rest api
    Given user send the following ABC Json as a GET request
    Then User should receive the 200 status code
    And User should receive following XYZ Json as a response body
