package fr.bendev.seatgeekapp.framework.network.model

import com.google.gson.annotations.SerializedName

data class ApiStats(
    @SerializedName("average_price")
    val averagePrice: Float?,
    @SerializedName("lowest_price")
    val lowestPrice: Float?,
    @SerializedName("highest_price")
    val highestPrice: Float?
)
