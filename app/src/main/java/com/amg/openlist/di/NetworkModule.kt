package com.amg.openlist.di

import androidx.annotation.NonNull
import com.amg.openlist.constants.MovieConstants
import com.amg.openlist.data.remote.MovieService
import com.amg.openlist.data.remote.RequestInterceptor
import com.amg.openlist.data.remote.ResponseInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(gson: Gson, @NonNull okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieConstants.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(MovieConstants.API_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(MovieConstants.API_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(MovieConstants.API_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(RequestInterceptor())
        .addInterceptor(ResponseInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

}