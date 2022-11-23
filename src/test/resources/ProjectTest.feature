Feature: Adding Project
  As a developer
  I want to add a project to my digital profile
  So that I can showcase my work to potential employers
  Background:
    Given A developer with digital profile id "2" wants to add a project to his digital profile

  @project-adding
  Scenario: Add Project
    When A Project Request is sent with values "This is a project description", "This is an icon Url","Project name", "Project Url"
    Then A Project with status code 201 is received