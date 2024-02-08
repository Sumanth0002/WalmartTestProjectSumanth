package com.walmart.test.model

import com.walmart.test.model.data.Country


interface CountriesDataSource {
    suspend fun getCountries(): List<Country>
}