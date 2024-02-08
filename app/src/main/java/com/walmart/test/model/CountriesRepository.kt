package com.walmart.test.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepository(
    private val countriesDataSource: CountriesDataSource
) {
    suspend fun getCountries() = withContext(Dispatchers.IO) {
        countriesDataSource.getCountries()
    }
}