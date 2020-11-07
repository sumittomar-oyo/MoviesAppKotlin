package com.example.movieappkotlin.view_model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movieappkotlin.data_source.MovieOverviewDataSource
import com.example.movieappkotlin.model.Movie
import java.util.*

class MovieOverviewViewModel2(application: Application) :
    AndroidViewModel(application) {
    var movieLiveData: MutableLiveData<ArrayList<Movie>> = MutableLiveData<ArrayList<Movie>>()
    var movieList: ArrayList<Movie> = ArrayList<Movie>()
    val movieMutableLiveData: MutableLiveData<ArrayList<Movie>>
        get() = movieLiveData

    fun init(application: Application) {
        movieLiveData.setValue(movieList)
        var mdb: MovieOverviewDataSource  = MovieOverviewDataSource()

        val cm =
            application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            mdb.fetchfromServer(application,movieLiveData)
        } else {
            mdb.fetchfromRoom(application,movieLiveData)
        }
    }


    init {
        init(application)
    }
}


