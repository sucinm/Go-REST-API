@Test
Feature: Get users

  Scenario: Get user without parameter
    Given the user without parameter
    When sent request user
    Then should return status code 200
    And validate json schema list user

    Scenario Outline: Get user with parameter gender
      Given the user with gender "<gender>"
      When sent request user
      Then should return status code 200
      And validate json schema list user
      And validate first data user gender should be "<gender>"
      Examples:
        | gender |
        | male   |

  Scenario Outline: Get user with parameter name
    Given the user with name "<name>"
    When sent request user
    Then should return status code 200
    And validate json schema list user
    And validate first data user name should be contain "<name>"
    Examples:
      | name |
      | oki  |

  Scenario Outline: Get user with parameter status
    Given the user with status "<status>"
    When sent request user
    Then should return status code 200
    And validate json schema list user
    And validate first data user status should be "<status>"
    Examples:
      | status   |
      | active   |
      | inactive |

  Scenario Outline: Get user with parameter email
    Given the user with email "<email>"
    When sent request user
    Then should return status code 200
    And validate json schema list user
    And validate first data user email should be "<email>"
    Examples:
      | email                              |
      | embranthiri_ms_kamala@johnston.org |

  Scenario: Get user with all parameter
    When sent request user with "name=Malik&gender=female&status=active&email=malik_sanya@crooks-ebert.org" as parameter
    Then should return status code 200

  Scenario: Get user with invalid parameter
    When sent request user with "user=Malik" as parameter
    Then should return status code 400





