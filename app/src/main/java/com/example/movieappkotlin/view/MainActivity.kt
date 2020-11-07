package com.example.movieappkotlin.view

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.movieappkotlin.R
import com.example.movieappkotlin.adapter.MovieOverviewAdapter
import com.example.movieappkotlin.adapter.MoviePagerAdapter
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.utilities.NetworkChangeReceiver
import com.example.movieappkotlin.utilities.SliderTimer
import com.example.movieappkotlin.view_model.MovieOverviewViewModel2
import java.util.*


class MainActivity : AppCompatActivity() , LifecycleOwner {
    private lateinit var context: Activity
    private lateinit var popularButton: Button
    private lateinit var movieOverviewAdapter: MovieOverviewAdapter
    private lateinit var nowPlayingButton: Button
    private lateinit var ratingButton: Button
    private lateinit var popularOverview: RecyclerView
    private lateinit var movieOverviewViewModel: MovieOverviewViewModel2
    private lateinit var viewPager: ViewPager
    private lateinit var moviePagerAdapter:MoviePagerAdapter
    private lateinit var timer: Timer
    private lateinit var mNetworkReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        mNetworkReceiver = MyReciever()
        registerNetworkBroadcast();

    }
    inner class MyReciever : NetworkChangeReceiver() {
        override fun DoWhat() {
            val intent = Intent(context, MainActivity::class.java)
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
    private fun initialize() {
        context = this;
        popularButton = findViewById(R.id.main_popular_btn)
        nowPlayingButton = findViewById(R.id.main_now_playing_btn)
        ratingButton = findViewById(R.id.main_rating_btn)
        popularOverview = findViewById(R.id.main_popular_movie_overview)
        viewPager = findViewById(R.id.main_viewpager)
        popularOverview.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
        )

        movieOverviewViewModel = ViewModelProviders.of(this)[MovieOverviewViewModel2::class.java]
        movieOverviewViewModel.movieLiveData.observe(this, movieListUpdateObserver)
        timer = Timer()


        popularButton.setOnClickListener { openNewActivity("popular") }

        nowPlayingButton.setOnClickListener { openNewActivity("nowplaying") }

        ratingButton.setOnClickListener { openNewActivity("rating") }

    }

    private fun openNewActivity(clickedButton: String?) {
        val intent = Intent(this, MovieListView::class.java)
        intent.putExtra("CLICKED_BUTTON", clickedButton)
        startActivity(intent)
    }

    fun openMovieDetailsActivity(view: View) {
        var tt :TextView? = view.findViewById<TextView>(R.id.main_movie_cover_id)
        if (tt == null) {
            tt = view.findViewById(R.id.movie_list_id)
        }
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("MOVIE_ID", tt!!.text)
        startActivity(intent)
    }


    var movieListUpdateObserver: Observer<ArrayList<Movie>> = Observer<ArrayList<Movie>> { movieList ->
        movieOverviewAdapter = MovieOverviewAdapter(context, movieList)
        moviePagerAdapter = MoviePagerAdapter(context, movieList)
        viewPager.adapter = moviePagerAdapter
        popularOverview.adapter = movieOverviewAdapter
        timer.scheduleAtFixedRate(SliderTimer(this@MainActivity, viewPager, 20), 5000, 5000)
    }

}