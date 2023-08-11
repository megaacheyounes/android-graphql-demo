package com.younes.graphqlcountriesapp.presentation

import com.younes.graphqlcountriesapp.domain.CountryDetails

data class CountryState(
    val isLoading: Boolean  = false,
    val countries:List<CountryDetails>? = null,
    val selectedCountry: CountryDetails ? = null,
    val error:String? = null
)