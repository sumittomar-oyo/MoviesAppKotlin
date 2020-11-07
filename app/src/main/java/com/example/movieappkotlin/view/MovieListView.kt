package com.example.movieappkotlin.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.R
import com.example.movieappkotlin.adapter.MovieListAdapter
import com.example.movieappkotlin.utilities.NetworkChangeReceiver
import com.example.movieappkotlin.view_model.MovieListViewModel
import com.example.movieappkotlin.view_model.MovieListViewModelFactory

class MovieListView : AppCompatActivity() {

    private lateinit var my_recycler_view: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MovieListAdapter
    private lateinit var movieListViewModal: MovieListViewModel
    private lateinit var CLICKED_BUTTON: String
    private lateinit var context : Context
    private lateinit var mNetworkReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_view)
        CLICKED_BUTTON = intent.getStringExtra("CLICKED_BUTTON")!!

        when (CLICKED_BUTTON) {
            "popular" -> this.title =
                "Popular Movies"
            "nowplaying" -> this.title =
                "Now Playing"
            else -> this.title = "Top Rated"
        }

        Toast.makeText(this, CLICKED_BUTTON, Toast.LENGTH_LONG).show()

        initialization()
        getMovieArticles()
        mNetworkReceiver = MyReciever()
        registerNetworkBroadcast();
    }

    inner class MyReciever : NetworkChangeReceiver() {
        override fun DoWhat() {
            val intent = Intent(context, MovieListView::class.java)
            intent.putExtra("CLICKED_BUTTON", CLICKED_BUTTON)
            startActivity(intent)
        }
    }

    private fun registerNetworkBroadcast() {
        registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    protected fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChanges()
    }

    private fun initialization() {
        context = this
        my_recycler_view = findViewById<View>(R.id.movie_list_view) as RecyclerView
        layoutManager = LinearLayoutManager(this@MovieListView)
        my_recycler_view.layoutManager = layoutManager
        my_recycler_view.setHasFixedSize(true)
        adapter = MovieListAdapter(this)
        movieListViewModal =
            ViewModelProviders.of(this, MovieListViewModelFactory(application, CLICKED_BUTTON)).get(
                MovieListViewModel::class.java
            )
    }

    private fun getMovieArticles() {
        movieListViewModal.itemPagedList.observe(this) { movies ->
            Log.e("errror", movies.snapshot().size.toString())

            adapter.submitList(movies)
        }

        my_recycler_view.adapter = adapter
    }



    fun openMovieDetailsActivity(view: View) {
        val tt = view.findViewById<TextView>(R.id.movie_list_id)
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("MOVIE_ID", tt.text)
        startActivity(intent)
    }
}