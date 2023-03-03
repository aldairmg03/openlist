package com.amg.openlist.di

import com.amg.openlist.data.local.MovieDao
import com.amg.openlist.data.remote.MovieRemoteDS
import com.amg.openlist.data.remote.MovieService
import com.amg.openlist.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService) =
        MovieRemoteDS(movieService)

    @Provides
    fun provideRepository(
        remoteDataSource: MovieRemoteDS,
        localDataSource: MovieDao
    ) = MovieRepository(remoteDataSource, localDataSource)

}