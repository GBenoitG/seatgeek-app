package fr.bendev.seatgeekapp.navigation.main

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.bendev.seatgeekapp.pages.events.EventsScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Events.route,
        modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        composable(route = Events.route) {
            EventsScreen(title = stringResource(id = Events.pageTitleRes))
        }
    }
}