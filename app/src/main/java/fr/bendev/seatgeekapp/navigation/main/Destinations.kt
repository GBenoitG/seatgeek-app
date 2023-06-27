package fr.bendev.seatgeekapp.navigation.main

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import fr.bendev.seatgeekapp.R
import timber.log.Timber

interface Destinations {
    val route: String

    @get:StringRes
    val pageTitleRes: Int
}

interface DestinationsWithArgs : Destinations {
    val routeWithArgs: String
    val arguments: List<NamedNavArgument>

    fun buildRouteWithArg(vararg args: String): String
}

object Events : Destinations {
    override val route: String
        get() = "events"

    @get:StringRes
    override val pageTitleRes: Int
        get() = R.string.events_page_title
}

object EventDetails : DestinationsWithArgs {

    override val route: String
        get() = "event"

    @get:StringRes
    override val pageTitleRes: Int
        get() = R.string.event_details_page_title

    const val eventIdArg = "event_id"
    const val eventNameArg = "event_name"

    override val routeWithArgs: String
        get() = "$route/{$eventIdArg}_{$eventNameArg}"
    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(eventIdArg) { type = NavType.LongType },
            navArgument(eventNameArg) { type = NavType.StringType }
        )

    override fun buildRouteWithArg(vararg args: String): String {
        if (args.size != arguments.size) throw IllegalArgumentException("Missing or too much arguments")

        val builtRoute = routeWithArgs
            .replace("{$eventIdArg}", args[0])
            .replace("{$eventNameArg}", args[1])

        Timber.d("builtRoute: $builtRoute")
        return builtRoute
    }
}