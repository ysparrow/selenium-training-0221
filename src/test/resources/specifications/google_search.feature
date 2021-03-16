Feature: Search with Google
  With Google I can search information in the Internet

  Scenario: I search articles about Selenium in the Internet
    Given Google search is opened in my browser
    When I enter search term "Selenium"
    Then Search results contains links for "Selenium" related resources
    And Search results page contains 12 results
    When I click second result
    Then Something Happened


  Scenario: Search for GlobalLogic
    Given Google search is opened in my browser
    When I enter search term "Global Logic"
    Then Search results contains links for "GlobalLogic" related resources
    When I click on first search results
    Then I see GlobalLogic main page
    And all elements are present


