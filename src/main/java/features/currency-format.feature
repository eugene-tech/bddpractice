Feature: Main page Сurrency List language

  Scenario Outline: Сurrency List language (#REQ-102)
    Given main page is opened
    When  user changes language to '<lang>'
    Then  Open currency list
    Then  Check if currency list was translated correctly based on language '<lang>'
    Examples:
      | lang    |
      | Русский |
      | English |
      | Română  |

