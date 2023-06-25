package fr.bendev.seatgeekapp.pages

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import fr.bendev.seatgeekapp.base.BaseActivity
import fr.bendev.seatgeekapp.navigation.main.MainNavHost

class MainActivity : BaseActivity() {

    @Composable
    override fun SetContentView() {
        val navController = rememberNavController()

        MainNavHost(navController = navController)
    }
}