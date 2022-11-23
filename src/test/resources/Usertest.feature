Feature: User Adding
  As possible user
  I want to register as User through the API
  So that it can be available to applications

  Background:
    Given The Endpoint to add user "http://localhost:%d/api/v1/users" is available

  @user-adding
    Scenario: Add a User
      When A User Request is sent with values "Abel", "Cierto", "cierto1000000@gmail.com", "993293832", "Abel#1234", "driver", "I am a Driver", "urlrImage", "urlBannerImage"
      Then A User with status 201 is received

  @delete-user
    Scenario: Delete a User
        When A User Delete is sent with id "2"
        Then A User with status 200 is received
  @get-user-by-id
    Scenario: Get a User by id
        When A User Selected is sent with id "3"
        Then A User with status 200 is received

  @get-all-users
    Scenario: Get all Users
        When A User who are registered in DB
        Then A List of Customer with status 200 is received

Feature: User Update
  As User
  I want to update my data through the API
  So to be able to make corrections to my data

  @update-user-by-id
  Scenario: Update a User by id
    When A User Update is sent with id id "2", "Zen", "Turrones", "zen990@gmail.com", "932121245", "zenT#12345", "driver", "I am a expert Driver", "https://image", "https://banner_image"
    Then A User with status 200 is received
