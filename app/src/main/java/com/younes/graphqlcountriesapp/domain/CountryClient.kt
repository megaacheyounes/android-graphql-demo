package com.younes.graphqlcountriesapp.domain


interface CountryClient {
    suspend fun getCountries(): List<CountryDetails>

    suspend fun getCountryDetails(id: String): CountryDetails?
}