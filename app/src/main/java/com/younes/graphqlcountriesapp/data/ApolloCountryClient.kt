package com.younes.graphqlcountriesapp.data

import com.apollographql.apollo3.ApolloClient
import com.younes.CountriesQuery
import com.younes.CountryQuery
import com.younes.graphqlcountriesapp.domain.CountryClient
import com.younes.graphqlcountriesapp.domain.CountryDetails

class ApolloCountryClient(
    private val aplloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<CountryDetails> {
        return aplloClient.query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toCountryDetails() }
            ?: emptyList()
    }

    override suspend fun getCountryDetails(id: String): CountryDetails? {
        return aplloClient.query(CountryQuery(id)).execute()
            .data
            ?.country
            ?.toCountryDetails()
    }
}