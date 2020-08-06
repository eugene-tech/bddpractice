# language: ru
Функция: Конвертор валют

  @req200
  Структура сценария: Конвертор валют подсчитывает значения согласно BNM (#REQ-200)
    Допустим главная страница открыта
    Если пользователь выбирает необходимые валюты '<слева>', '<справа>'
    И вводит сумму '<сумма>'
    Тогда отображается сумма эквивалентная введенной для выбранной валюты
    Примеры:
      | слева     | справа    | сумма     |
      | MDL       | EUR       | 207.13    |
      | ${random} | ${random} | ${random} |
      | ${random} | ${random} | ${random} |

  @req200
  Структура сценария: Официальный курс отображает историю курса BNM (#REQ-201)
    Допустим главная страница открыта
    Если пользователь меняет язык на '<язык>'
    И отрывает страницу с официальным курсом валют
    Тогда таблица Курсы НБМ отображается
    К тому же отрывает окно выбора валют
    К тому же показывается курс за последние три дня
    Примеры:
      | язык    |
      | Русский |