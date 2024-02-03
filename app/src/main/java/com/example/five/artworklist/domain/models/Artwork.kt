package com.example.five.artworklist.domain.models
import com.google.gson.annotations.SerializedName


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