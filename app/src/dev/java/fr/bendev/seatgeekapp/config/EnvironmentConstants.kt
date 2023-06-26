package fr.bendev.seatgeekapp.config

import fr.bendev.seatgeekapp.BuildConfig

object EnvironmentConstants {

    /**
     * baseUrl constant is used to PRODUCTION ENVIRONMENT
     */
    const val baseUrl: String = "https://${BuildConfig.BASE_URL}/"

    /**
     * API credentials as environment variable
     */
    const val clientId: String = BuildConfig.CLIENT_ID
    const val clientSecret: String = BuildConfig.CLIENT_SECRET
}