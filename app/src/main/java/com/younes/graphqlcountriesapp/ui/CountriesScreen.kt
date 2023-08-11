package com.younes.graphqlcountriesapp.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.younes.graphqlcountriesapp.domain.CountryDetails
import com.younes.graphqlcountriesapp.presentation.CountryState
import com.younes.graphqlcountriesapp.ui.theme.GraphQlCountriesAppTheme


@Composable
fun CountriesScreen(
    modifier: Modifier = Modifier,
    state: CountryState = CountryState(),
    onCountrySelected: (countryDetails: CountryDetails) -> Unit = {},
    onDialogDismiss: () -> Unit = { }
) {

    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {


        state.countries?.let {
            LazyColumn(
                Modifier.fillMaxSize(),
            ) {
                items(it.size) { index ->
                    val country = state.countries[index]
                    CountryListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { onCountrySelected(country) },
                        countryDetails = state.countries[index]
                    )
                }
            }
        }

        state.selectedCountry?.let {
            CountryDialog(
                modifier = Modifier ,
                countryDetails = it,
                onDismiss = onDialogDismiss
            )
        }

        if (state.isLoading) {
            LinearProgressIndicator(
                Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
fun CountryListItem(
    modifier: Modifier = Modifier,
    countryDetails: CountryDetails = getDummyCountry()
) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = countryDetails.emoji,
                fontSize = 38.sp,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground
            )
            Column(
                Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = countryDetails.name,
                    style = MaterialTheme.typography.h6,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    text = countryDetails.code,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }

}


@Composable
fun CountryDialog(
    modifier: Modifier = Modifier,
    countryDetails: CountryDetails = getDummyCountry(),
    onDismiss: () -> Unit = {}
) {

    val values = mapOf(
        "Name" to countryDetails.name,
        "Capital" to countryDetails.capital,
        "Currency" to countryDetails.currency,
        "Continent" to countryDetails.continent,
        "languages" to remember(countryDetails.languages) {
            countryDetails.languages?.joinToString()
        }
    )

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.background)
                .padding(32.dp)
        ) {

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = countryDetails.emoji,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(Modifier.height(8.dp))

            values.map { entry ->
                Row(
                    Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "${entry.key}:",
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface

                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = entry.value ?: "unknown",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun CountryItemPreview() {
    GraphQlCountriesAppTheme {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
//        CountryItem()
            CountryDialog()
        }
    }
}

fun getDummyCountry() = CountryDetails(
    "ggg",
    "Banana",
    "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F",
    "test"
)
