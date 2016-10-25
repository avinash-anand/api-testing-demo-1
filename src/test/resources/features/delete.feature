Feature: This feature will test the DELETE request of a REST API

  @suite
  Scenario: Verify the delete request send by rest api
    Given User wants to delete registration by id
    Then User should receive "200" as Status code
    And User should receive following response body
      | key              | value                |
      | registrationId   | 987654321            |
      | status           | Deleted              |
      | registrationDate | 2016-10-24           |
      | processingDate   | 2016-10-25T09:30:47Z |
      | isActive         | true                 |
      | cost             | 100.75               |

