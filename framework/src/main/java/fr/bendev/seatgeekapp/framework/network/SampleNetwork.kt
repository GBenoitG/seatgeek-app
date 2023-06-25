package fr.bendev.seatgeekapp.framework.network

import com.google.gson.GsonBuilder
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
class SampleNetwork private constructor(private val baseUrl: String) {

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
            //.addNetworkInterceptor(/*SampleInterceptor()*/)
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

        fun setBaseUrl(baseUrl: String) = apply {
            this@Builder.baseUrl = baseUrl
        }

        /**
         * Build function must tests all mandatory variables throw and exception then return an
         * instance of SampleNetwork
         *
         * @throws UninitializedPropertyAccessException
         * @return SampleNetwork
         */
        fun build(): SampleNetwork {
            baseUrl ?: throw UninitializedPropertyAccessException("Error: baseUrl cannot be null")

            return SampleNetwork(baseUrl!!)
        }

    }

}