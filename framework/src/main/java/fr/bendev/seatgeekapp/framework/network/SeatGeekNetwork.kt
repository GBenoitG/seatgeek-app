package fr.bendev.seatgeekapp.framework.network

import com.google.gson.GsonBuilder
import fr.bendev.seatgeekapp.framework.network.interceptors.AuthInterceptor
import fr.bendev.seatgeekapp.framework.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * DESCRIPTION: Object class which create instance of all retrofit services depending of retrofit
 * instance itself.
 * Uses the Retrofit library
 * FILE NAMING CONVENTION: {OBJECT}Network
 */
class SeatGeekNetwork private constructor(
    private val baseUrl: String,
    private val clientId: String,
    private val clientSecret: String
) {

    /**
     * Instance of a service creates with Retrofit instance
     * NAMING CONVENTION: val {object}Service: {Object}Service by lazy { retrofit.create({Object}Service::class.java) }
     *
     *
        val sampleService: SampleService by lazy {
            retrofit.create(SampleService::class.java)
        }
    */

    /**
     * Instance of httpClient
     */
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .writeTimeout(Constants.NETWORK_TIMEOUT_IN_S, TimeUnit.SECONDS)
            .readTimeout(Constants.NETWORK_TIMEOUT_IN_S, TimeUnit.SECONDS)
            .addNetworkInterceptor(AuthInterceptor(clientId, clientSecret))
            //.authenticator(/*RefreshTokenAuthenticator()*/)
            .build()
    }

    /**
     * Instance of gson
     */
    private val gson by lazy {
        GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create()
    }

    /**
     * Instance of retrofit
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    /**
     * Builder pattern that allow build an instance with the best configuration we need.
     */
    class Builder {
        private var baseUrl: String? = null
        private var clientId: String? = null
        private var clientSecret: String? = null

        fun setBaseUrl(baseUrl: String) = apply {
            this@Builder.baseUrl = baseUrl
        }

        fun setApiCredentials(clientId: String, clientSecret: String) = apply {
            this@Builder.clientId = clientId
            this@Builder.clientSecret = clientSecret
        }

        /**
         * Build function must tests all mandatory variables throw and exception then return an
         * instance of SeatGeekNetwork
         *
         * @throws UninitializedPropertyAccessException
         * @return SeatGeekNetwork
         */
        fun build(): SeatGeekNetwork {
            baseUrl ?: throw UninitializedPropertyAccessException("Error: baseUrl cannot be null")
            clientId ?: throw UninitializedPropertyAccessException("Error: clientId cannot be null")
            clientSecret ?: throw UninitializedPropertyAccessException("Error: clientSecret cannot be null")

            return SeatGeekNetwork(baseUrl!!, clientId!!, clientSecret!!)
        }

    }

}