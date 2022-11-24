Feature: Adding Programming Language
  As a developer
  I want to add a programming language to my digital profile
    So that I can show my skills to potential employers
  Background:
    Given A developer with digital profile id "2" wants to add a programming language to his digital profile

  @programmingLanguage-adding
  Scenario: Add Programming Language
    When A Programming Language Request is sent with values "This is a programming language description", "This is an icon Url","Programming Language name"
    Then A Programming Language with status code 201 is received