package fr.bendev.seatgeekapp.framework.network.model

import com.google.gson.annotations.SerializedName

data class ApiPerformer(
    @SerializedName("image")
    val image: String,
)
