package com.walmart.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.walmart.test.databinding.ActivityMainBinding
import com.walmart.test.model.CountriesDataSourceImpl
import com.walmart.test.model.CountriesRepository
import com.walmart.test.model.getCountriesAPI
import com.walmart.test.view.CountriesAdapter
import com.walmart.test.viewmodel.CountriesViewModel
import com.walmart.test.viewmodel.CountriesViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val countriesAPI = getCountriesAPI()
        val countriesDataSource = CountriesDataSourceImpl(countriesAPI)
        val countriesRepository = CountriesRepository(countriesDataSource)
        CountriesViewModelFactory(countriesRepository).create(CountriesViewModel::class.java).apply {
            countriesResponse.observe(this@MainActivity) {
                binding.rvCountriesInfo.isVisible = true
                binding.rvCountriesInfo.adapter = CountriesAdapter(it)
                binding.pbLoading.isVisible = false
                binding.tvServiceFailed.isVisible = false
            }
            serviceFailure.observe(this@MainActivity) {
                binding.pbLoading.isVisible = false
                binding.rvCountriesInfo.isVisible = false
                binding.tvServiceFailed.isVisible = true
                binding.tvServiceFailed.text = it.localizedMessage
            }
            binding.pbLoading.isVisible = true
            getCountries()
        }
    }
}