package fr.bendev.seatgeekapp.framework.network.services

import fr.bendev.seatgeekapp.framework.network.model.ApiEvent
import fr.bendev.seatgeekapp.framework.network.model.ApiEventsPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val DEFAULT_PER_PAGE_NUMBER = 25

interface EventsService {

    @GET("events")
    suspend fun getEventsPage(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE_NUMBER
    ): Response<ApiEventsPage>

    @GET("events/{eventId}")
    suspend fun getEventById(
        @Path("eventId") id: Long
    ): Response<ApiEvent>

}