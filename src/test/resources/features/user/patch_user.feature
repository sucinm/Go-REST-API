@Test
Feature: Patch users

  Scenario: Update user with valid ID and valid request body
    Given Update user with valid ID and request body
    When send request patch user
    Then should return status code 200
    And validate json schema update user

  Scenario: Update user with valid ID and invalid request body
    Given update user with valid ID and request body
    When send request patch user
    Then should return status code 422

  Scenario: Update user with invalid ID and valid request body
    Given update user with "#@" as ID and "valid" request body
    When send request patch user
    Then should return status code 400

  Scenario: Update user with invalid ID and invalid request body
    Given update user with "#@" as ID and "invalid" request body
    When send request patch user
    Then should return status code 200
    And response body message should be "name cannot blank"

  Scenario: Update user without token
    Given update user without token
    When send request patch user
    Then should return status code 401

  Scenario: Update user without ID
    Given update user with "" as ID and "valid" request body
    When send request patch user
    Then should return status code 401





