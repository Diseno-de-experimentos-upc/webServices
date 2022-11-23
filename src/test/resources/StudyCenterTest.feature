Feature: Adding Study Center
  As a developer
  I want to add a study center to my education
  So that I can show my education to recruiters
  Background:
    Given A developer with education id "1" wants to add a study center to his education

  @studyCenter-adding
  Scenario: Add Study Center
    When A Study Center Request is sent with values "This is a study center description", "10/07/2020", "10/07/2025", "This is an icon Url", "Boston University", "50"
    Then A Study Center with status code 201 is received