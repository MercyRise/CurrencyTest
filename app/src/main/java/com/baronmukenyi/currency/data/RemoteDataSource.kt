package com.baronmukenyi.currency.data

import com.baronmukenyi.currency.models.ConvertCurrency
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val currencyRatesAPI: CurrencyRatesAPI) {
    suspend fun getExchangeRates(queries: Map<String, String>): Response<ConvertCurrency>{
        return currencyRatesAPI.getExchangeRates(queries)
    }
}