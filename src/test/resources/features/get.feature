Feature: Get API testing

  @suite
  Scenario: User wants to get registration by id
    Given User has established GET connection
    When User requests for registration detail
    Then User should see "200" as Status code
    And User should receive following response body
      | key            | value     |
      | registrationId | 123456789 |



