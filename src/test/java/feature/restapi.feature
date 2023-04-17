Feature: Test the Rest APIs

  Scenario: Test the Get operation for Users
    Given User send the URL "https://gorest.co.in"
    When User send the resources as "public/v2/users" with "Get" call
    Then User expect a response as 200

  Scenario: Test the Get operation for Posts
    Given User send the URL "https://gorest.co.in"
    When User send the resources as "public/v2/posts" with "Get" call
    Then User expect a response as 200

  Scenario: Test the Get operation for Comments
    Given User send the URL "https://gorest.co.in"
    When User send the resources as "public/v2/comments" with "Get" call
    Then User expect a response as 200

  Scenario: Test the Get operation for TODO
    Given User send the URL "https://gorest.co.in"
    When User send the resources as "public/v2/todos" with "Get" call
    Then User expect a response as 200

  Scenario: Test the Post operation
    Given User send the URL "https://gorest.co.in"
    When User send the headers as Key Value pair
    |  Key                  |  Value                           |
    |  Content-Type         |  application/json                |
    |  Authorization        |  Bearer 235e225d46a4dfcdc38beab23d582f83981ac2b46fe6cbce38cbf42d3717d6cd             |
    And User adds the "postRequest.json" body to the request
    And User send the resources as "public/v2/users" with "Post" call
    Then User expect a response as 201

  Scenario: Test the Delete Operation
    Given User send the URL "https://gorest.co.in"
    When User send the headers as Key Value pair
      |  Key                  |  Value                           |
      |  Content-Type         |  application/json                |
      |  Authorization        |  Bearer 235e225d46a4dfcdc38beab23d582f83981ac2b46fe6cbce38cbf42d3717d6cd             |
    And User send the resources as "public/v2/users/1036917" with "Delete" call
    Then User expect a response as 204
