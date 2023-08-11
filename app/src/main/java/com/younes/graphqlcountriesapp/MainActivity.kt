package com.younes.graphqlcountriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.younes.graphqlcountriesapp.presentation.CountriesViewModel
import com.younes.graphqlcountriesapp.ui.CountriesScreen
import com.younes.graphqlcountriesapp.ui.theme.GraphQlCountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQlCountriesAppTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            backgroundColor = MaterialTheme.colors.primaryVariant,
                            title = {
                                Text(
                                    style = MaterialTheme.typography.h5,
                                    text = "GraphQl demo",
                                    color = MaterialTheme.colors.onPrimary,
                                )
                            })
                    },
                    content = { padding ->
                        CountriesScreen(
                            modifier = Modifier.padding(padding),
                            state = state,
                            onCountrySelected = viewModel::selectCountry,
                            onDialogDismiss = viewModel::deselectCountry
                        )
                    }
                )

            }
        }
    }
}