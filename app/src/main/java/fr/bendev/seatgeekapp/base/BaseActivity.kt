package fr.bendev.seatgeekapp.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import fr.bendev.seatgeekapp.theme.SeatGeekTheme

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeatGeekTheme {
                SetContentView()
            }
        }
    }

    @Composable
    abstract fun SetContentView()

}