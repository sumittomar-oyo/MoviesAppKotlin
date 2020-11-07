package com.example.movieappkotlin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappkotlin.R
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.Movie

class MovieListAdapter(private val mCtx: Context) :
    PagedListAdapter<Movie, MovieListAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(mCtx).inflate(R.layout.list_each_row_movies, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val movie: Movie? = getItem(i)
        if (movie != null) {
            viewHolder.movieTitle.text = movie.title
            viewHolder.movieId.text = movie.id.toString() + ""
            viewHolder.movieOverView.text = movie.overview
            Log.e("rrr", MovieConstants.BASE_IMAGE + movie.poster_path)
            Glide.with(mCtx)
                .load(MovieConstants.BASE_IMAGE + movie.poster_path)
                .into(viewHolder.imgViewMoviesCover)
        } else {
            Toast.makeText(mCtx, "article is null", Toast.LENGTH_LONG).show()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle = itemView.findViewById<View>(R.id.movie_list_title) as TextView
        val movieOverView = itemView.findViewById<View>(R.id.movie_list_overview) as TextView
        val movieId = itemView.findViewById<View>(R.id.movie_list_id) as TextView
        val imgViewMoviesCover: ImageView = itemView.findViewById<View>(R.id.movie_list_img) as ImageView

    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldMovie: Movie, newMovie: Movie): Boolean {
                    return newMovie.title === newMovie.title
                }

                override fun areContentsTheSame(oldMovie: Movie, newMovie: Movie): Boolean {
                    return oldMovie == newMovie
                }
            }
    }
}
