package com.walmart.test.model

import com.walmart.test.model.CountriesAPI.Companion.BASE_URL
import com.walmart.test.model.data.Country
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesAPI {

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/"
    }

    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): List<Country>
}

fun getCountriesAPI(): CountriesAPI {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountriesAPI::class.java)
}