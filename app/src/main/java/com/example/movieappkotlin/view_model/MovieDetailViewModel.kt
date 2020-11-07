package com.example.movieappkotlin.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.MovieDetailed
import com.example.movieappkotlin.network.VolleySingleton
import com.google.gson.GsonBuilder


class MovieDetailViewModal(application: Application, movieId: String?) :
    ViewModel() {
    var movieMutableLiveData: MutableLiveData<MovieDetailed?>? = null
    var movie: MovieDetailed? = null
    fun init(application: Application?, movieId: String?) {
        movieMutableLiveData = MutableLiveData()
        Log.e("Movvvii", "" + movieId)
        movie = MovieDetailed()
        movieMutableLiveData!!.value = movie
        populateList(application, movieId)
    }

    fun populateList(application: Application?, movieId: String?) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            MovieConstants.MOVIE_DETAIL_URL.toString() + movieId + MovieConstants.API_KEY,
            null,
            { response ->
                try {
                    if (response != null) {
                        val gson = GsonBuilder().create()
                        Log.e("here", "here")
                        movie = gson.fromJson(response.toString(), MovieDetailed::class.java)
                        movieMutableLiveData!!.postValue(movie)
                    }
                } catch (e: Exception) {
                    Log.e("error", e.toString())
                    e.printStackTrace()
                }
            }
        ) { error -> Log.e("ErrorResponse", error.toString()) }
        VolleySingleton.getInstance(application!!).requestQueue.add(jsonObjectRequest)
    }

    init {
        init(application, movieId)
    }
}
