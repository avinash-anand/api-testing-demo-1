@wip

Feature: This feature will test the DELETE request of a REST API

  Scenario: Verify the delete request send by rest api
    Given I send the following parameter as a DELETE request
      | Parameter        | Value                |
      | registrationId   | 123456789            |
      | status           | Approved             |
      | registrationDate | 2016-10-24           |
      | processingDate   | 2016-10-25T09:30:47Z |
      | isActive         | true                 |
      | cost             | 100.75               |
    Then User should receive the following status code
      | Status Code | 200 |
    And User should receive following response body
      | key              | value                |
      | registrationId   | 123456789            |
      | status           | Approved             |
      | registrationDate | 2016-10-24           |
      | processingDate   | 2016-10-25T09:30:47Z |
      | isActive         | true                 |
      | cost             | 100.75               |