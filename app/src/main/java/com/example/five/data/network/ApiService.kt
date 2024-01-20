package com.example.five.data.network

import com.example.five.data.models.ApiResponseArtworks
import com.example.five.data.models.ApiResponseTypes
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/artwork-types")
    suspend fun getArtworkTypes(): ApiResponseTypes
    @GET("/api/v1/artworks/search")
    suspend fun searchArtworks(
        @Query("q") query: String,
        @Query("fields") fields: String = "id,title,image_id,artist_title,artist_display,artist_id,artwork_type_id,artwork_type_title,config,material_titles,credit_line,dimensions,date_display,image_id,alt_image_ids"
    ): ApiResponseArtworks
}