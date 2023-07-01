package fr.bendev.seatgeekapp.framework.network.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ApiEvent(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("datetime_utc")
    val date: Date,
    @SerializedName("enddatetime_utc")
    val endDate: Date?,
    @SerializedName("venue")
    val venue: ApiVenue,
    @SerializedName("stats")
    val stats: ApiStats,
    @SerializedName("performers")
    val performers: List<ApiPerformer>,
)
