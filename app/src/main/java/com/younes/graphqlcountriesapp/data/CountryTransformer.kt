package com.younes.graphqlcountriesapp.data

import com.younes.CountriesQuery
import com.younes.CountryQuery
import com.younes.graphqlcountriesapp.domain.CountryDetails


fun CountriesQuery.Country.toCountryDetails(): CountryDetails {
    return CountryDetails(
        code = code,
        name = name,
        capital = capital ?: "unknown",
        emoji = emoji
    )
}

fun CountryQuery.Country.toCountryDetails(): CountryDetails {
    return CountryDetails(
        code = code,
        name = name,
        capital = capital ?: "unknown",
        emoji = emoji,
        currencies = currencies,
        currency = currency,
        languages = languages.toSimpleLanguageList(),
        continent = continent.name
    )
}

fun List<CountryQuery.Language>.toSimpleLanguageList(): List<String> {
    return this.map { it.name }
}