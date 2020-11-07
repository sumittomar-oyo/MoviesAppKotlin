package com.example.movieappkotlin.constants

object MovieConstants {
    const val POPULAR_URL_MOVIE =
        "https://api.themoviedb.org/3/movie/popular?api_key=6804e7ea097203de38a96d011138b924&page="
    const val NOWPLAYING_URL_MOVIE =
        "https://api.themoviedb.org/3/movie/now_playing?api_key=6804e7ea097203de38a96d011138b924&page="
    const val RATING_URL_MOVIE =
        "https://api.themoviedb.org/3/movie/top_rated?api_key=6804e7ea097203de38a96d011138b924&page=" // the size of a page that we want
    const val PAGE_SIZE = 100
    const val BASE_IMAGE = "https://image.tmdb.org/t/p/w185"
    const val MOVIE_DETAIL_URL = "https://api.themoviedb.org/3/movie/"
    const val FIRST_PAGE = 1
    const val QUERY = "movies"
    const val API_KEY = "?api_key=6804e7ea097203de38a96d011138b924"
}
