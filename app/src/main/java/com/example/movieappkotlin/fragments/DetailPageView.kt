package com.example.movieappkotlin.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movieappkotlin.R
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.MovieDetailed
import java.io.Serializable

class DetailPageView : Fragment() {
    // TODO: Rename and change types of parameters
    private val mParam1: String? = null
    private val mParam2: String? = null
    private var movie: MovieDetailed? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        movie = arguments!!.getSerializable(MOVIE_KEY) as MovieDetailed?
        val view: View = inflater.inflate(R.layout.fragment_detail_page_view, container, false)
        val imageView = view.findViewById<ImageView>(R.id.detail_movie_img)
        val title = view.findViewById<TextView>(R.id.detail_movie_title)
        val rating = view.findViewById<TextView>(R.id.detail_movie_rating)
        Log.e("erewr",MovieConstants.BASE_IMAGE+movie!!.poster_path)
        Glide.with(this)
                .load(MovieConstants.BASE_IMAGE + movie!!.poster_path)
                .into(imageView)
        title.text = movie!!.title
        rating.text = "" + movie!!.vote_average
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        private const val MOVIE_KEY = "movie_key"
        fun newInstance(movie: MovieDetailed?): DetailPageView {
            val fragment = DetailPageView()
            val args = Bundle()
            args.putSerializable(MOVIE_KEY, movie as Serializable?)
            fragment.arguments = args
            return fragment
        }
    }
}