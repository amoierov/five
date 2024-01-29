package com.example.five.categorylist.domain.models

import com.google.gson.annotations.SerializedName

data class ArtworkTypesResponse(
    @SerializedName("data")
    val data: List<ArtworkType>,
)
