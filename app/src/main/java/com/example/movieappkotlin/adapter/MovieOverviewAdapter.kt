package com.example.movieappkotlin.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappkotlin.R
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.Movie
import java.util.*

class MovieOverviewAdapter(var context: Activity, movieList: ArrayList<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var movieList: ArrayList<Movie>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.movie_overview, parent, false)
        //        View rootView = LayoutInflater.from(context).inflate(R.layout.popular_movie_overview,null);
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie: Movie = movieList[position]
        val recyclerViewViewHolder = holder as RecyclerViewViewHolder
        Glide.with(context)
                .load(MovieConstants.BASE_IMAGE + movie.poster_path)
                .into(recyclerViewViewHolder.imageView)
        recyclerViewViewHolder.textViewId.text = movie.id.toString() + ""
        recyclerViewViewHolder.textView.text = movie.title
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun submitList(movies: ArrayList<Movie>) {
        movieList = movies
    }

    internal inner class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.main_movie_cover_img)
        var textView: TextView = itemView.findViewById(R.id.main_movie_cover_title)
        var textViewId: TextView = itemView.findViewById(R.id.main_movie_cover_id)

    }

    init {
        this.movieList = movieList
        Log.e("MovieList2", "" + movieList.size)
    }
}
