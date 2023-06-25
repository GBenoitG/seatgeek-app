package fr.bendev.seatgeekapp.framework.network.interceptors

import fr.bendev.seatgeekapp.framework.utils.Constants
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val clientId: String,
    private val clientSecret: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val credentials = Credentials.basic(clientId, clientSecret)
        request.header(Constants.HEADER_AUTH, credentials)

        return chain.proceed(request.build())
    }
}