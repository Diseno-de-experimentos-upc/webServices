Feature: Adding Database
  As a developer
  I want to add a database to my digital profile
  So that I can show that I have experience with a particular database
  Background:
    Given A developer with digital profile id "2" wants to add a database to his digital profile

  @database-adding
  Scenario: Add Database
    When A Database Request is sent with values "This is a database description", "This is an icon Url","Database name"
    Then A Database with status code 201 is received