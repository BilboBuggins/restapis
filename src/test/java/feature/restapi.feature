Feature: Test the Rest APIs

  Scenario: Test the Get operation for Users
    Given I send the URL "https://gorest.co.in"
    When I send the resources as "public/v2/users"
    Then I expect a response as 200

  Scenario: Test the Get operation for Posts
    Given I send the URL "https://gorest.co.in"
    When I send the resources as "public/v2/posts"
    Then I expect a response as 200

  Scenario: Test the Get operation for Comments
    Given I send the URL "https://gorest.co.in"
    When I send the resources as "public/v2/comments"
    Then I expect a response as 200

  Scenario: Test the Get operation for TODO
    Given I send the URL "https://gorest.co.in"
    When I send the resources as "public/v2/todos"
    Then I expect a response as 200
