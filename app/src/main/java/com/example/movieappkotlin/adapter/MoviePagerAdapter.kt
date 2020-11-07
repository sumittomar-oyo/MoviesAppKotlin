package com.example.movieappkotlin.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.movieappkotlin.R
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.Movie


class MoviePagerAdapter(private val activity: Activity, movies: ArrayList<Movie>) : PagerAdapter() {
    private val movies: ArrayList<Movie> = movies
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.list_each_row_movies, null)
        val imageView = view.findViewById<ImageView>(R.id.movie_list_img)
        val textView1 = view.findViewById<TextView>(R.id.movie_list_title)
        val textView2 = view.findViewById<TextView>(R.id.movie_list_overview)
        val textView3 = view.findViewById<TextView>(R.id.movie_list_id)
        textView1.text = movies[position].title
        textView2.text = movies[position].overview
        textView3.text = movies[position].id.toString()

        Log.e("comisndfs","dsifnsd");
        Glide.with(activity)
                .load(MovieConstants.BASE_IMAGE + movies[position].poster_path)
                .into(imageView)
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

}