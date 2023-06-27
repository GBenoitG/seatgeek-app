package fr.bendev.seatgeekapp.framework.network.model

import com.google.gson.annotations.SerializedName

data class ApiPageInfo(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
)