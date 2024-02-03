package com.example.five.data.models

import com.example.five.categorylist.domain.models.ArtworkType
import com.google.gson.annotations.SerializedName

data class ArtworkTypesResponse(
    @SerializedName("data")
    val data: List<ArtworkType>,
)
