Feature: Notifications in the web app
  As a User developer
  I want to receive notifications in the web app

  Background:
    Given the User developer with id "1" is in notifications section

  @notifications-delete
  Scenario: Delete a notification
    When the User developer clicks on the delete button in the notification with id "1" from recruiter with id "3"
    Then the notification is deleted with success code 200