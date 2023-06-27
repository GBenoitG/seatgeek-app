package fr.bendev.seatgeekapp.navigation.main

import androidx.annotation.StringRes
import fr.bendev.seatgeekapp.R

interface Destinations {
    val route: String
    @get:StringRes
    val pageTitleRes: Int
}

interface DestinationsWithArgs : Destinations {
    fun buildRouteWithArg(vararg args: String): String
}

object Events : Destinations {
    override val route: String
        get() = "events"
    override val pageTitleRes: Int
        get() = R.string.events_page_title
}