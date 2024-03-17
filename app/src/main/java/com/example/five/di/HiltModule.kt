package com.example.five.di

import android.content.Context
import androidx.room.Room
import com.example.five.BuildConfig
import com.example.five.data.local.SavedArtworkDao
import com.example.five.data.local.SavedArtworkDatabase
import com.example.five.data.network.ApiService
import com.example.five.data.repository.RoomRepositorySavedArtworkImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    private const val BASE_URL = "https://api.artic.edu/"

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .cookieJar(CookieJar.NO_COOKIES) // Поддержка всех cookie
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideSavedArtworkDatabase(@ApplicationContext context: Context): SavedArtworkDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SavedArtworkDatabase::class.java,
            "artwork_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSavedArtworkDao(database: SavedArtworkDatabase): SavedArtworkDao {
        return database.savedArtworkDao()
    }

    @Singleton
    @Provides
    fun provideSavedArtworkRepository(artworkDao: SavedArtworkDao): RoomRepositorySavedArtworkImpl {
        return RoomRepositorySavedArtworkImpl(artworkDao)
    }
}