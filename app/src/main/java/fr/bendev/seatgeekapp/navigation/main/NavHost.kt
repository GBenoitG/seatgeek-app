package fr.bendev.seatgeekapp.navigation.main

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.bendev.seatgeekapp.pages.main.MainScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Main.route,
        modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        composable(Main.route) {
            MainScreen(title = stringResource(id = Main.pageTitleRes))
        }
    }
}