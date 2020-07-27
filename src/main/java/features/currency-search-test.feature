Feature: Main page Сurrency Search
  @testTag
  Scenario Outline: Сurrency Search (#REQ-300)
    Given main page is opened
    When  user open currency list
    And   send any '<symbols>' in search input
    Then  check if output search results is expected
    Examples:
      | symbols    |
      | р          |
      | ${any}     |
      | ${any}     |
      | +          |


