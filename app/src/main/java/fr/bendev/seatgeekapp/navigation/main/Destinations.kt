package fr.bendev.seatgeekapp.navigation.main

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import fr.bendev.seatgeekapp.R

interface Destinations {
    val route: String
    @get:StringRes
    val pageTitleRes: Int
}

interface DestinationsWithArgs : Destinations {
    fun buildRouteWithArg(vararg args: String): String
}

object Main : Destinations {
    override val route: String
        get() = "main"
    override val pageTitleRes: Int
        get() = R.string.app_name
}