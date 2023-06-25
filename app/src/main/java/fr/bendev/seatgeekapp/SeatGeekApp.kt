package fr.bendev.seatgeekapp

import android.app.Application
import fr.bendev.seatgeekapp.framework.di.Injector
import fr.bendev.seatgeekapp.config.EnvironmentConstants
import timber.log.Timber

class SeatGeekApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        Injector
            .initNetwork {
                // set up the base url of network
                setBaseUrl(baseUrl = EnvironmentConstants.baseUrl)
                // set up credentials of network
                setApiCredentials(
                    clientId = EnvironmentConstants.clientId,
                    clientSecret = EnvironmentConstants.clientSecret
                )
            }
            .build(this)

    }
}