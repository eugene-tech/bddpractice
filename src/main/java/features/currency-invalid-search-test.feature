Feature: Main page Invalid Сurrency Search
  @testTag
  Scenario Outline: Invalid Сurrency Search (#REQ-301)
    Given main page is opened
    When  user open currency list
    And   send any '<symbols>' in search input
    Then  check if output search results is invalid and matches with pattern -> No results match <Search symbol>
    Examples:
      | symbols    |
      | +          |
      | invalid    |


