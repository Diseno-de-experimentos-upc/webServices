Feature: User Update
  As User
  I want to update my data through the API
  So to be able to make corrections to my data

  Background:
    Given The Endpoint to update user "http://localhost:%d/api/v1/users" is available

  @update-user-by-id
  Scenario: Update a User by id
    When A User update is sent with id "2", "Zen", "Turrones", "zen990@gmail.com", "932121245", "zenT#12345", "driver", "I am a expert Driver", "https://image", "https://banner_image"
    Then A User update with status 200 is received