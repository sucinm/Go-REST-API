@Test
Feature: Delete users

  Scenario: Delete User with valid ID
    Given delete User with valid ID
    When send request delete user
    Then should return status code 204

  Scenario: Delete User with invalid ID
    Given delete User with "!@#" ID
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
