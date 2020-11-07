package com.example.movieappkotlin.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.data_source.MovieListDataSourceFactory
import com.example.movieappkotlin.model.Movie

class MovieListViewModel(application: Application, CLICKED_BUTTON: String) :
    ViewModel() {
    var itemPagedList: LiveData<PagedList<Movie>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Movie>>

    companion object {
        private val TAG = MovieListViewModel::class.java.simpleName
    }

    init {
        Log.d(TAG, "ArticleViewModel called")
        val movieDataSourceFactory = MovieListDataSourceFactory(application, CLICKED_BUTTON)

        //getting the live data source from data source factory
        liveDataSource = movieDataSourceFactory.getItemLiveDataSource()

        Log.e("model","1")
        // Getting PagedList configuration
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(MovieConstants.PAGE_SIZE).build()

        Log.e("model","2")
        // Building the paged list
        itemPagedList =
            LivePagedListBuilder(movieDataSourceFactory, pagedListConfig).build()
    }
}