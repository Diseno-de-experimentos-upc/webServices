Feature: Adding Framework
  As a developer
  I want to add a framework to my digital profile
    So that I can show that I have experience with a particular framework
  Background:
    Given A developer with digital profile id "2" wants to add a framework to his digital profile

  @framework-adding
  Scenario: Add Framework
    When A Framework Request is sent with values "This is a framework description", "This is an icon Url","Framework name"
    Then A Framework with status code 201 is received