package com.baronmukenyi.currency.utils

import com.baronmukenyi.currency.models.CurrencyAndCountry

class Constants {
    companion object{

        const val APP_BAR_TITLE = "Currency\nConverter"

        const val BASE_URL = "https://api.exchangeratesapi.io/"

        val CURRENCY_CODES_LIST = listOf(
            CurrencyAndCountry("Australia", "AUD"),
            CurrencyAndCountry("Brazil", "BRL"),
            CurrencyAndCountry("Bulgaria", "BGN"),
            CurrencyAndCountry("Canada", "CAD"),
            CurrencyAndCountry("China", "CYN"),
            CurrencyAndCountry("Croatia", "HRK"),
            CurrencyAndCountry("Czech Republic", "CZK"),
            CurrencyAndCountry("Danmark", "DKK"),
            CurrencyAndCountry("European Union", "EU"),
            CurrencyAndCountry("Great Britain", "GBP"),
            CurrencyAndCountry("Hong Kong", "HKD"),
            CurrencyAndCountry("Hungary", "HUF"),
            CurrencyAndCountry("Iceland", "ISK"),
            CurrencyAndCountry("India", "INR"),
            CurrencyAndCountry("indonesia", "IDR"),
            CurrencyAndCountry("Israel", "ILS"),
            CurrencyAndCountry("Japan", "JPY"),
            CurrencyAndCountry("Korea", "KWR"),
            CurrencyAndCountry("Malaysia", "MYR"),
            CurrencyAndCountry("Mexico", "MXN"),
            CurrencyAndCountry("New Zealand", "NZD"),
            CurrencyAndCountry("Norway", "NOK"),
            CurrencyAndCountry("Philippines", "PHP"),
            CurrencyAndCountry("Poland", "PLN"),
            CurrencyAndCountry("Romania", "RON"),
            CurrencyAndCountry("Russia", "RUB"),
            CurrencyAndCountry("Singapour", "SGD"),
            CurrencyAndCountry("South Africa", "ZA"),
            CurrencyAndCountry("Sweden", "SEK"),
            CurrencyAndCountry("Australia", "CHF"),
            CurrencyAndCountry("Thailand", "THB"),
            CurrencyAndCountry("Turkey", "TRY"),
            CurrencyAndCountry("United States", "USD"),

        )
    }
}