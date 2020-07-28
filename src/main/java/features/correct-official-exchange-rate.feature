Feature: Correct Official Exchange Rate
  @testTag
  Scenario: Correct Official Exchange Rate #REQ-201
    Given main page is opened
    Then  Open Exchange rates page
    Then  Click on official exchange rate menu option
    Then  Check if the official exchange rate displayed the correct course for the last three days based on bnm exchange rate

