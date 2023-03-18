@Test
Feature: Delete users

  Scenario: Delete User with valid ID
    Given delete User with valid ID
    When send request delete user
    Then should return status code 204

  Scenario: Delete User with unregistered ID
    Given delete User with "1" ID
    When send request delete user
    Then should return status code 404
    And response body message should be "Resource not found"

  Scenario: Delete User with invalid format ID
    Given delete User with "!@#" ID
    When send request delete user
    Then should return status code 400

  Scenario: Delete User with name as a path parameter
    Given delete User with "Elakshi" ID
    When send request delete user
    Then should return status code 400

  Scenario: Delete User with email as a path parameter
    Given delete User with "amb_sethi_priya@herman.net" ID
    When send request delete user
    Then should return status code 400

  Scenario: Delete User without ID
    Given delete User with "" ID
    When send request delete user
    Then should return status code 404

  Scenario: Delete User without ID and without Token
    Given delete User without ID and without token
    When send request delete user
    Then should return status code 401
    And response body message should be "Authentication failed"
    And validate json schema response error
