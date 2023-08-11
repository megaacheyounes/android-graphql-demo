package com.younes.graphqlcountriesapp.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.younes.graphqlcountriesapp.domain.CountryClient
import com.younes.graphqlcountriesapp.domain.CountryDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountriesViewModel @Inject constructor(
    val client: CountryClient
) : ViewModel() {

    private var _state = MutableStateFlow(CountryState())
    var state = _state as StateFlow<CountryState>

    init {
        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = client.getCountries().sortedBy { it.name.substring(0..1) },
                    isLoading = false
                )
            }

        }
    }

    fun selectCountry(countryDetails: CountryDetails) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            _state.update {
                it.copy(
                    isLoading = false,
                    selectedCountry = client.getCountryDetails(countryDetails.code)
                )
            }

        }
    }

    fun deselectCountry() {
        _state.update {
            it.copy(
                selectedCountry = null
            )
        }

    }


}