@wip

Feature: This feature will test the Long DELETE request & response of a REST API

  Scenario: Verify the long delete request & response , send & received by rest api
    Given user send the following ABC Json as a DELETE request
    Then User should receive the 200 status code
    And User should receive following XYZ Json as a response body
