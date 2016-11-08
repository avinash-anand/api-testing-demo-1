Feature: This feature will test the PUT request of a REST API

  @suite
  Scenario: Verify the put request send by rest api
    Given User sends the following parameters as a registration object as a PUT request
      | Parameter        | Value                |
      | registrationId   | 123456789            |
      | status           | Approved             |
      | registrationDate | 2016-10-24           |
      | processingDate   | 2016-10-25T09:30:47Z |
      | isActive         | true                 |
      | cost             | 100.75               |
    Then User should receive "200" as Status code
    And User should receive following response body
      | key                | value                |
      | registrationId     | 987654321            |
      | status             | Pending              |
      | registrationDate   | 2016-10-24           |
      | processingDate     | 2016-10-25T09:30:47Z |
      | isActive           | false                |
      | cost               | 100.75               |
      | parent.node1       | value1               |
      | parent.booleanNode | true                 |
    And User should receive following response body key and types
      | key                | type     |
      | registrationId     | int      |
      | status             | string   |
      | registrationDate   | date     |
      | processingDate     | datetime |
      | isActive           | boolean  |
      | cost               | double   |
      | nullKey            | null     |
      | parent             | object   |
      | parent.node1       | string   |
      | parent.booleanNode | boolean  |

