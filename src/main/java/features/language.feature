Feature: Language change

  Scenario Outline: Language changed at menu (#REQ-101)
    Given main page is opened
    When user changes language to '<lang>'
    Then menu translates to chosen language and consists of '<links>'
    And tab name changes to '<tab>'
    Examples:
      | lang    | links                                                                                   | tab                      |
      | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Curs.md - Курсы валют    |
      | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | Curs.md - Exchange rates |
      | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Curs.md - Curs valutar   |