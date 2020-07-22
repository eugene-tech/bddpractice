Feature: Official Exchange Rate

  Scenario Outline: Official Exchange Rate (#REQ-103)
    Given main page is opened
    When  user changes language to '<lang>'
    Then  Open Exchange rates page
    Then  Click on official exchange rate menu option
    Then  Check if official exchange rate was translated correctly based on language '<lang>'
    Examples:
      | lang    |
      | Русский |
      | English |
      | Română  |

