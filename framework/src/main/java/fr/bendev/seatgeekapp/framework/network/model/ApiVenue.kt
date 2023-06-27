package fr.bendev.seatgeekapp.framework.network.model

import com.google.gson.annotations.SerializedName

data class ApiVenue(
    @SerializedName("address")
    val address: String?,
    @SerializedName("country")
    val country: String,
    @SerializedName("city")
    val city: String,
)