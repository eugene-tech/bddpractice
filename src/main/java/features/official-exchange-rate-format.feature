Feature: Official Exchange Rate

  Scenario Outline: Official Exchange Rate (#REQ-103)
    Given Main page is opened
    When  Page language was changed on '<lang>'
    Then  Open Exchange rates page
    Then  Click on official exchange rate menu option
    Then  Check if official exchange rate was translated correctly based on language '<lang>'
    Examples:
      | lang    |
      | Русский |
      | English |
      | Română  |

