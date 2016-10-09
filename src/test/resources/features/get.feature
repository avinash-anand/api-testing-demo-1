Feature: Get API testing

  @suite
  Scenario: User wants to get registration by id
    Given User has established GET connection
    When User requests for registration detail
    Then User should see "200" as Status code
    And User should receive following response body
      | key              | value                |
      | registrationId   | 123456789            |
      | status           | Approved             |
      | registrationDate | 2016-10-24           |
      | processingDate   | 2016-10-25T09:30:47Z |
      | isActive         | true                 |
      | cost             | 100.75               |
    And User should receive following response body key and types
      | key              | type     |
      | registrationId   | int      |
      | status           | string   |
      | registrationDate | date     |
      | processingDate   | datetime |
      | isActive         | boolean  |
      | cost             | double   |




