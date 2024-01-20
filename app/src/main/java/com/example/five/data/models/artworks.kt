package com.example.five.data.models
import com.google.gson.annotations.SerializedName


data class ApiResponseTypes(
    @SerializedName("data")
    val data: List<ArtworkType>,
)

data class ApiResponseArtworks(
    @SerializedName("data")
    val data: List<Artwork>,
)

data class ArtworkType(
    @SerializedName("title")
    val title: String
)

data class Artwork(
    @SerializedName("artist_title")
    val artistTitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("date_display")
    val dateDisplay: String,
    @SerializedName("image_id")
    val imageId: String
)