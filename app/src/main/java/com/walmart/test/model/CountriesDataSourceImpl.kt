package com.walmart.test.model

import com.walmart.test.model.data.Country

class CountriesDataSourceImpl(
    private val api: CountriesAPI
): CountriesDataSource {
    override suspend fun getCountries(): List<Country> {
        return api.getCountries()
    }
}