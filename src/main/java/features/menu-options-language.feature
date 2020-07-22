Feature: Menu options language
  #@testTag
  Scenario Outline: Menu options language (#REQ-104)
    Given Main page is opened
    When  Page language was changed on '<lang>'
    Then  We should click on '<menu>' option and check if '<pageTabHeader>' and '<tabPage>' was translated correctly
    Examples:
      | lang    | menu                                                  | tabPage                                                                                                                                             |pageTabHeader                                                                                                                                             |
      | Русский | Эволюция курсов, Банки, Валютные кассы, Валюты        | График эволюции валюты Евро в Banca Nationala относительно Молдавский лей, Curs.md - Банки, Curs.md - Валютные кассы, Curs.md - Валюты              |График эволюции валюты Евро в Banca Nationala относительно Молдавский лей, Список коммерческих банков, Список валютных касс, Валюты мира                  |
      | English | Rates evolution, Banks, Exchange offices, Currencies  | Graph of Euro rates evolution in Banca Nationala relative to Moldovan Leu, Curs.md - Banks, Curs.md - Exchange offices,Curs.md - Currencies         |Graph of Euro rates evolution in Banca Nationala relative to Moldovan Leu, List of commercial banks, List of exchange offices, Currencies of the world    |
      | Română  | Grafic evolutii, Bănci, Case de schimb valutar,Valute | Graficul evolutiei valutei Euro la Banca Nationala raportat la Leu moldovenesc, Curs.md - Bănci, Curs.md - Case de schimb valutar, Curs.md - Valute |Graficul evolutiei valutei Euro la Banca Nationala raportat la Leu moldovenesc, Lista băncilor comerciale, Lista caselor de schimb valutar, Valutele lumii|
