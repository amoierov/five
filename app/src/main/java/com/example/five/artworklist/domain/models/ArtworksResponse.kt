package com.example.five.artworklist.domain.models

import com.google.gson.annotations.SerializedName

data class ArtworksResponse(
    @SerializedName("data")
    val data: List<Artwork>,
)
