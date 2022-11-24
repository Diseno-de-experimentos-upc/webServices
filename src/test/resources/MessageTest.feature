Feature: Send a message from recruiter to developer
  As a recruiter
  I want to send a message to a developer
  So that it can chat with a developer.

  Background:
    Given  A User recruiter with id "3" is chatting with User developer with id "1"

  @user-send-message
  Scenario: Send a message from recruiter to developer
    When The User recruiter sends a message to User developer with content "Hello, I'm a recruiter from Facebook"
    Then the message is sent from recruiter to developer with status code 201