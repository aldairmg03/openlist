package com.amg.openlist.di

import android.content.Context
import com.amg.openlist.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideMovieDatabase(
        @ApplicationContext appContext: Context
    ) = MovieDatabase.getDatabase(appContext)

    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()

}