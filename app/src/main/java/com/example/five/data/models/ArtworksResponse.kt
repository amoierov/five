package com.example.five.data.models

import com.example.five.artworklist.domain.models.Artwork
import com.google.gson.annotations.SerializedName

data class ArtworksResponse(
    @SerializedName("data")
    val data: List<Artwork>,
)
