package com.example.five.data.network

import com.example.five.data.models.ArtworksResponse
import com.example.five.data.models.ArtworkTypesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val ARTWORK_SEARCH_FIELDS = "id,title,image_id,artist_title," +
                "artist_display,artist_id,artwork_type_id,artwork_type_title,config," +
                "material_titles,credit_line,dimensions,date_display,image_id,alt_image_ids, gallery_title"
    }
    @GET("api/v1/artwork-types")
    suspend fun getArtworkTypes(): ArtworkTypesResponse
    @GET("/api/v1/artworks/search")
    suspend fun searchArtworks(
        @Query("q") query: String,
        @Query("fields") fields: String = ARTWORK_SEARCH_FIELDS
    ): ArtworksResponse
}