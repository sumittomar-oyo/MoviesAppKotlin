package com.example.movieappkotlin.data_source

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.paging.PageKeyedDataSource
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.network.VolleySingleton
import com.example.movieappkotlin.response.MovieResponse
import com.example.movieappkotlin.utilities.ResolveURL
import com.google.gson.GsonBuilder

class MovieApiListDataSource internal constructor(var context: Context, CLICKED_BUTTON: String) :
    PageKeyedDataSource<Int, Movie>() {
    var url =""

    override fun loadInitial( params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        val stringReq = JsonObjectRequest(
            Request.Method.GET, url + "1", null,
            { response ->
                try {
                    if (response != null) {
                        val gson = GsonBuilder().create()
                        val movieResponse: MovieResponse? = gson.fromJson(
                            response.toString(),
                            MovieResponse::class.java
                        )
                        callback.onResult(
                            movieResponse?.movies as MutableList<Movie>,
                            null,
                            MovieConstants.FIRST_PAGE + 1
                        )
                    }
                } catch (e: Exception) {
                    Log.e("error", e.toString())
                    e.printStackTrace()
                }
            }
        ) { error ->
            Toast.makeText(context, "Hello Javatpoint start", Toast.LENGTH_SHORT).show()
            Log.e("error first", error.toString())
        }
        VolleySingleton.getInstance(context).requestQueue.add(stringReq)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

    /*
     * This method it is responsible for the subsequent call to load the data page wise when user scroll
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val stringReq = JsonObjectRequest(
            Request.Method.GET, url + params.key, null,
            { response ->
                try {
                    if (response != null) {
                        val gson = GsonBuilder().create()
                        val movieResponse: MovieResponse = gson.fromJson(
                            response.toString(),
                            MovieResponse::class.java
                        )
                        val key =
                            when {
                                params.key == movieResponse.totalResults -> null
                                else -> params.key + 1
                            }
                        callback.onResult(movieResponse.movies as MutableList<Movie>, key)
                    }
                } catch (e: Exception) {
                    Log.e("error", e.toString())
                    e.printStackTrace()
                }
            }
        ) { Toast.makeText(context, "Hello Javatpoint 2", Toast.LENGTH_SHORT).show() }
        VolleySingleton.getInstance(context).requestQueue.add(stringReq)
    }


    /*
     * This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    init {
        url = ResolveURL.getURL(CLICKED_BUTTON)
    }
}
