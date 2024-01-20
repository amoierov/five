package com.example.five.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    private const val BASE_URL = "https://api.artic.edu/"
    private const val TIMEOUT_SECONDS = 60L
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)

//            // Логирование запросов и ответов
//            Log.d("Network", "Request: ${request.url}")
//            Log.d("Network", "Headers: ${request.headers}")
//            Log.d("Network", "Body: ${request.body}")
//            Log.d("Network", "Response: ${response.code}")
//            Log.d("Network", "Response Body: ${response.body?.string()}")

            response
        })
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    val retrofit: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}


