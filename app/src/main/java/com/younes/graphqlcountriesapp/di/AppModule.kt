package com.younes.graphqlcountriesapp.di

import com.apollographql.apollo3.ApolloClient
import com.younes.graphqlcountriesapp.data.ApolloCountryClient
import com.younes.graphqlcountriesapp.domain.CountryClient
import com.younes.graphqlcountriesapp.presentation.CountriesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloCountryClient(client: ApolloClient): ApolloCountryClient {
        return ApolloCountryClient(client)
    }

    @Provides
    @Singleton
    fun provideCountriesViewModel(
        client: ApolloCountryClient
    ): CountriesViewModel {
        return CountriesViewModel(
            client
        )
    }

}