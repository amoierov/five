package com.example.five.models

data class ArtworkType(
    val id: Int,
    val apiModel: String,
    val apiLink: String,
    val title: String,
    val aatId: Int,
    val sourceUpdatedAt: String
)

data class Artwork(
    val score: Double,
    val artistDisplay: String,
    val artistTitle: String,
    val title: String,
    val artistId: Int,
    val creditLine: String,
    val artworkTypeTitle: String,
    val altImageIds: List<String>,
    val dateDisplay: String,
    val id: Int,
    val imageId: String,
    val materialTitles: List<String>,
    val artworkTypeId: Int,
    val dimensions: String
)