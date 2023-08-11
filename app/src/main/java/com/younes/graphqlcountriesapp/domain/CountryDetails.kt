package com.younes.graphqlcountriesapp.domain

data class CountryDetails(
    val code:String,
    val name: String,
    val emoji: String,
    val capital: String,
    val languages: List<String>? = null,
    val currency: String? = null,
    val currencies: List<String>? = null,
    val continent:String? = null
)

