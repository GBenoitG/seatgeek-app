package fr.bendev.seatgeekapp.navigation.main

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.bendev.seatgeekapp.R
import fr.bendev.seatgeekapp.navigation.navigateSingleTopTo
import fr.bendev.seatgeekapp.pages.event.EventDetailsScreen
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
            EventsScreen(
                title = stringResource(id = Events.pageTitleRes),
                onEventClick = { id, name ->
                    navController.navigateSingleTopTo(
                        EventDetails.buildRouteWithArg(
                            id.toString(),
                            name
                        )
                    )
                }
            )
        }
        composable(
            route = EventDetails.routeWithArgs,
            arguments = EventDetails.arguments
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getLong(EventDetails.eventIdArg)?.let {
                EventDetailsScreen(
                    id = it,
                    title = stringResource(id = R.string.event_details_page_title),
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}