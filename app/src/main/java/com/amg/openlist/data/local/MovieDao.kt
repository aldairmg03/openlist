package com.amg.openlist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amg.openlist.data.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE category LIKE :category")
    suspend fun getMoviesByCategory(category: String): List<MovieEntity>

}