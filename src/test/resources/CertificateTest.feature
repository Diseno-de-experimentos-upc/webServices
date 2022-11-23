Feature: Adding Certificate
  As a developer
  I want to add a certificate to my education
  So that I can show my skills and knowledge
  Background:
    Given A developer with education id "1" wants to add a certificate to his education

  @certificate-adding
  Scenario: Add Certificate
    When A Certificate Request is sent with values "This is a certificate description", "This is an icon Url","11/10/2022", "Master in Java"
    Then A Certificate with status code 201 is received