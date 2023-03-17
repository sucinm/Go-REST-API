@Test
Feature: Post users

  Scenario: Create user with valid request body
    Given create user with valid request body
    When send request post user
    Then should return status code 201
    And validate json schema create user

  Scenario: Create user with invalid request body
    Given create user with invalid request body
    When send request post user
    Then should return status code 422

  Scenario: Create user without request body
    Given create user without request body
    When send request post user
    Then should return status code 400

  Scenario: Create user without name on request body
    Given create user without name on request body
    When send request post user
    Then should return status code 400
    And response body message should be "can't be blank"

  Scenario: Create user without token
    Given create user without token
    When send request post user
    Then should return status code 401

  Scenario: Create user without token and without request body
    When send request post user
    Then should return status code 401





