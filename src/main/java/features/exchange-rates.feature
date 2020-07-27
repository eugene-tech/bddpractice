Feature: Exchange Rates
  #@testTag
  Scenario Outline: Exchange Rates (#REQ-200)
    Given main page is opened
    When  user send '<sum>' for exchange
    And   user select currency code for exchange from '<charCode1>'
    Then  user select currency code for exchange to '<charCode2>' and check if output sum was exchanged based on bnm exchange rate

    Examples:
      | sum       | charCode1          | charCode2                  |
      | ${random} | MDL                | USD,EUR,RUB,RON,UAH        |
      | ${random} | USD                | MDL,EUR,RUB,RON,UAH        |
      | ${random} | EUR                | MDL,USD,RUB,RON,UAH        |
      | ${random} | RUB                | MDL,USD,EUR,RON,UAH        |
      | ${random} | RON                | MDL,USD,EUR,RUB,UAH        |
      | ${random} | UAH                | MDL,USD,EUR,RUB,RON        |
