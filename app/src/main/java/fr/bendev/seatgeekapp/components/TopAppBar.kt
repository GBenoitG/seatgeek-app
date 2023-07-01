package fr.bendev.seatgeekapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import fr.bendev.seatgeekapp.R

@Composable
fun TitleTopAppBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    Column(
        modifier = modifier.background(backgroundColor),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(dimensionResource(id = R.dimen.size_medium)),
            Arrangement.Start,
            Alignment.CenterVertically
        ) {
            navigationIcon()
            Spacer(modifier = Modifier.weight(1f))
        }
        title()
    }
}