Feature: Language change

  @langHeader
  Scenario Outline: Language changed at menu (#REQ-101)
    Given main page is opened
    When user changes language to '<lang>'
    Then menu translates to chosen language and consists of '<links>'
    And tab name changes to '<tab>'
    And header name changed to '<header>'
    And slogan changed to '<slogan>'
    Examples:
      | lang    | links                                                                                   | tab                      | header                                     | slogan                                    |
      | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Curs.md - Курсы валют    | Про нас, Получить курс, Контакты           | Твой проводник в мире валют               |
      | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | Curs.md - Exchange rates | About us, Get rates, Contacts              | Your daily source of exchange information |
      | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Curs.md - Curs valutar   | Despre noi, Preluare curs valutar, Contact | Sursa ta zilnică de informații valutare   |


  @langRates
  Scenario Outline: Language changed at tab 'Rates evolution'  (#REQ-104)
    Given main page is opened
    When user changes page to 'Rates evolution'
    Then page was changed to 'Rates evolution'
    And  user changes language to '<lang>'
    And menu translates to chosen language and consists of '<links>'
    And header name changed to '<header>'
    And slogan changed to '<slogan>'
    And main info was translated to chosen language '<mains>'
#      And relative translate '<relative>'
#      And bank translate '<bank>'
    Examples:
      | lang    | links                                                                                  | header                                     | slogan                                    | mains                                                                          | relative      | bank   |
      | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                 | Про нас, Получить курс, Контакты           | Твой проводник в мире валют               | График эволюции валюты Евро в Banca Nationala относительно Молдавский лей      | Относительно: | Банк:  |
      | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies        | About us, Get rates, Contacts              | Your daily source of exchange information | Graph of Euro rates evolution in Banca Nationala relative to Moldovan Leu      | Relative to:  | Bank:  |
      | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute| Despre noi, Preluare curs valutar, Contact | Sursa ta zilnică de informații valutare   | Graficul evolutiei valutei Euro la Banca Nationala raportat la Leu moldovenesc | Raportat la:  | Banca: |

  @langConverter
  Scenario Outline: Language changed at tab 'Convertor'  (#REQ-104)
    Given main page is opened
    When user changes page to 'Convertor'
    Then page was changed to 'Convertor'
    And  user changes language to '<lang>'
    And menu translates to chosen language and consists of '<links>'
    And tab name changes to '<tab>'
    And header name changed to '<header>'
    And slogan changed to '<slogan>'
    And operation options were translated to chosen language '<operations>'
    And name of convertor changes to chosen language '<convertors>'
    Examples:
      | lang    | links                                                                                   | header                                     |tab                         | slogan                                    | operations                                                                                        | convertors         |
      | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Про нас, Получить курс, Контакты           | Curs.md - Конвертер        | Твой проводник в мире валют               | операции за наличные, официальный курс, операции с банковскими картами, операции по перечислению  | Конвертер          |
      | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | About us, Get rates, Contacts              | Curs.md - Convertor        | Your daily source of exchange information | cash operations, official exchange rate, operations with cards, operations on accounts            | Convertor          |
      | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Despre noi, Preluare curs valutar, Contact | Curs.md - Convertor valutar| Sursa ta zilnică de informații valutare   | operatii in numerar, curs oficial, operatii cu carduri bancare, operatii prin virament            | Convertor valutar  |

    @langExchangeRates
    Scenario Outline: Language changed at tab 'Exchange rates'  (#REQ-104)
      Given main page is opened
      When user changes page to 'Exchange rates'
      Then page was changed to 'Exchange rates'
      And  user changes language to '<lang>'
      And menu translates to chosen language and consists of '<links>'
      And tab name changes to '<tab>'
      And header name changed to '<header>'
      And slogan changed to '<slogan>'
      And operation options were translated to chosen language '<operations>'
      And header name of form was changed '<headerform>'
      Examples:
        | lang    | links                                                                                   | header                                     | tab                         | slogan                                    | operations                                                                                        | headerform                    |
        | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Про нас, Получить курс, Контакты           | Curs.md - Курсы валют       | Твой проводник в мире валют               | операции за наличные, официальный курс, операции с банковскими картами, операции по перечислению  | Курсы коммерческих банков     |
        | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | About us, Get rates, Contacts              |Curs.md - Exchange rates     | Your daily source of exchange information | cash operations, official exchange rate, operations with cards, operations on accounts            | Commercial banks rates        |
        | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Despre noi, Preluare curs valutar, Contact |Curs.md - Curs valutar       | Sursa ta zilnică de informații valutare   | operatii in numerar, curs oficial, operatii cu carduri bancare, operatii prin virament            | Cotatiile bancilor comerciale |

    @langBank
    Scenario Outline: Language changed at tab 'Bank'  (#REQ-104)
      Given main page is opened
      When user changes page to 'Bank'
      Then page was changed to 'Bank'
      And  user changes language to '<lang>'
      And menu translates to chosen language and consists of '<links>'
      And tab name changes to '<tab>'
      And header name changed to '<header>'
      And slogan changed to '<slogan>'
      And name of form was translated to chosen language '<bankformname>'
      Examples:
        | lang    | links                                                                                   | header                                     | tab               | slogan                                    | bankformname                |
        | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Про нас, Получить курс, Контакты           | Curs.md - Банки   | Твой проводник в мире валют               | Список коммерческих банков  |
        | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | About us, Get rates, Contacts              | Curs.md - Banks   | Your daily source of exchange information | List of commercial banks    |
        | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Despre noi, Preluare curs valutar, Contact | Curs.md - Bănci   | Sursa ta zilnică de informații valutare   | Lista băncilor comerciale   |

  @langExchangeOffices
  Scenario Outline: Language changed at tab 'Exchange Offices'  (#REQ-104)
    Given main page is opened
    When user changes page to 'Exchange Offices'
    Then page was changed to 'Exchange Offices'
    And  user changes language to '<lang>'
    And menu translates to chosen language and consists of '<links>'
    And tab name changes to '<tab>'
    And header name changed to '<header>'
    And slogan changed to '<slogan>'
    And list of exchange was translated to chosen language '<exchangeoffices>'
    Examples:
      | lang    | links                                                                                   | header                                     | tab                               | slogan                                    | exchangeoffices                   |
      | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Про нас, Получить курс, Контакты           | Curs.md - Валютные кассы          | Твой проводник в мире валют               | Список валютных касс              |
      | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | About us, Get rates, Contacts              | Curs.md - Exchange offices        | Your daily source of exchange information | List of exchange offices          |
      | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Despre noi, Preluare curs valutar, Contact | Curs.md - Case de schimb valutar  | Sursa ta zilnică de informații valutare   | Lista caselor de schimb valutar   |

  @langCurrencies
  Scenario Outline: Language changed at tab 'Currencies'  (#REQ-104)
    Given main page is opened
    When user changes page to 'Currencies'
    Then page was changed to 'Currencies'
    And  user changes language to '<lang>'
    And menu translates to chosen language and consists of '<links>'
    And tab name changes to '<tab>'
    And header name changed to '<header>'
    And slogan changed to '<slogan>'
    And list of the currencies was changed '<currencies>'
    Examples:
      | lang    | links                                                                                   | header                                     | tab                               | slogan                                    | currencies                  |
      | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Про нас, Получить курс, Контакты           | Curs.md - Валюты                  | Твой проводник в мире валют               | Валюты мира                 |
      | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | About us, Get rates, Contacts              | Curs.md - Currencies              | Your daily source of exchange information | Currencies of the world     |
      | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Despre noi, Preluare curs valutar, Contact | Curs.md - Valute                  | Sursa ta zilnică de informații valutare   | Valutele lumii              |


