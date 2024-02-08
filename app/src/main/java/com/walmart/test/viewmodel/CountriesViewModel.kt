package com.walmart.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.walmart.test.model.CountriesRepository
import com.walmart.test.model.data.Country
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesViewModel(
    private val countriesRepository: CountriesRepository
): ViewModel() {
    private val _countriesResponse = MutableLiveData<List<Country>>()
    val countriesResponse: LiveData<List<Country>> = _countriesResponse

    private val _serviceFailure = MutableLiveData<Exception>()
    val serviceFailure: LiveData<Exception> = _serviceFailure

    fun getCountries() {
        viewModelScope.launch {
            try {
                _countriesResponse.postValue(countriesRepository.getCountries())
            } catch(e: Exception) {
                _serviceFailure.postValue(e)
            }
        }
    }
}

class CountriesViewModelFactory(private val countriesRepository: CountriesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountriesViewModel(countriesRepository) as T
    }
}