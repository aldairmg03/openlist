package com.amg.openlist.constants

object MovieConstants {

    const val API_KEY = "ff1541ffb94b89e3dc599b860dec920d"
    const val API_LANGUAGE = "en-US"
    const val API_URL = "https://api.themoviedb.org/3/"
    const val CATEGORY_TOP_RATED = "top_rated"

    const val API_TIMEOUT = 45L

    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w400"

    fun getPosterPath(posterPath: String): String {
        return BASE_POSTER_PATH + posterPath
    }

    const val MOVIE_DATABASE = "movie_db"
    const val MOVIE_SHARED_PREFERENCES = "movie_sp"
    const val DB_HAVE_DATA_SHA = "local_data_available"

}