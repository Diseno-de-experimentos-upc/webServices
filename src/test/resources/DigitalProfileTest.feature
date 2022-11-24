Feature: Adding Digital Profile
  As a developer
  I want to add a digital profile through the API
  So that I can add a digital profile to a user

  Background:
    Given The Endpoint "http://localhost:%d/api/v1/digital_profiles" is available

  @digitalProfile-adding
  Scenario: Add Digital Profile
    When A Digital Profile Request is sent with values "2", "digitalProfile1"
    Then A Digital Profile with status code 201 is received