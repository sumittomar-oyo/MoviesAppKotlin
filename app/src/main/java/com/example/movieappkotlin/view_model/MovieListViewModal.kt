package com.example.movieappkotlin.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.data_source.MovieListDataSourceFactory
import com.example.movieappkotlin.data_source.db.DatabaseClient
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.utilities.CheckNetworkConnectivity

class MovieListViewModel(application: Application, CLICKED_BUTTON: String) :
    ViewModel() {
    var itemPagedList: LiveData<PagedList<Movie>>

    companion object {
        private val TAG = MovieListViewModel::class.java.simpleName
    }

    init {
        val connectivity = CheckNetworkConnectivity().check(application)
        if(connectivity){
            val movieDataSourceFactory = MovieListDataSourceFactory(application, CLICKED_BUTTON)
            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(MovieConstants.PAGE_SIZE).build()
            itemPagedList =
                LivePagedListBuilder(movieDataSourceFactory, pagedListConfig).build()


        }
        else{
            val movies: DataSource.Factory<Int, Movie> =
                DatabaseClient.getInstance(application)!!.appDatabase.movieDao()!!.getAllPaged()

            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(MovieConstants.PAGE_SIZE).build()
            itemPagedList =
                LivePagedListBuilder(movies, pagedListConfig).build()
        }


    }
}