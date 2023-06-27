package fr.bendev.seatgeekapp.framework.network.model

import com.google.gson.annotations.SerializedName

data class ApiEventsPage(
    @SerializedName("events")
    val events: List<ApiEvent>,
    @SerializedName("meta")
    val pageInfo: ApiPageInfo,
)
