package com.example.movieappkotlin.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.network.VolleySingleton
import com.example.movieappkotlin.response.MovieResponse
import com.google.gson.GsonBuilder
import java.util.*

class MovieOverviewViewModel(application: Application) : AndroidViewModel(application) {
    var movieLiveData: MutableLiveData<ArrayList<Movie>> = MutableLiveData<ArrayList<Movie>>()
    var movieList: ArrayList<Movie>? = null

    private fun init(application: Application?) {
        populateList(application)
        movieLiveData.value = movieList
    }

    fun populateList(application: Application?) {
        movieList = ArrayList<Movie>()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, MovieConstants.POPULAR_URL_MOVIE.toString() + "1", null,
                { response ->
                    try {
                        if (response != null) {
                            val gson = GsonBuilder().create()
                            val movieResponse: MovieResponse =
                                    gson.fromJson(response.toString(), MovieResponse::class.java)
                            movieLiveData.setValue(movieResponse.movies as ArrayList<Movie>)
                        }
                    } catch (e: Exception) {
                        Log.e("error", e.toString())
                        e.printStackTrace()
                    }
                }
        ) { }
        if (application != null) {

            VolleySingleton.getInstance(application).requestQueue.add(jsonObjectRequest)
        }
    }

    init {
        init(application)
    }
}
