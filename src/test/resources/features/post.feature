

Feature: This feature will test the POST request of a REST API

  @suite
  Scenario: Verify the post request send by rest api
    Given User sends the following parameters as a registration object as a POST request
      | Parameter        | Value                |
      | registrationId   | 123456789            |
      | status           | Approved             |
      | registrationDate | 2016-10-24           |
      | processingDate   | 2016-10-25T09:30:47Z |
      | isActive         | true                 |
      | cost             | 100.75               |
    Then User should receive "200" as Status code
    And User should receive following response body
      | key              | value                |
      | registrationId   | 123456789            |
      | status           | Approved             |
      | registrationDate | 2016-10-24           |
      | processingDate   | 2016-10-25T09:30:47Z |
      | isActive         | true                 |
      | cost             | 100.75               |

