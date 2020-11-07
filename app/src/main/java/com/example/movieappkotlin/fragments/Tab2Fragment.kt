package com.example.movieappkotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movieappkotlin.R
import com.example.movieappkotlin.model.Genres
import com.example.movieappkotlin.model.MovieDetailed
import java.io.Serializable
import java.util.*

class Tab2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val mParam1: String? = null
    private val mParam2: String? = null
    var movie: MovieDetailed? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        movie = arguments!!.getSerializable(MOVIE_KEY) as MovieDetailed?
        val view: View = inflater.inflate(R.layout.fragment_tab2, container, false)
        val list: ArrayList<Genres> = movie!!.genres as ArrayList<Genres>
        var str = ""
        if (list != null) for (genre in list) {
            str += genre.name
            str+="\n"
        }
        val textView = view.findViewById<TextView>(R.id.detail_genre)
        textView.text = str
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        private const val MOVIE_KEY = "movie_key"
        fun newInstance(movie: MovieDetailed?): Tab2Fragment {
            val fragment = Tab2Fragment()
            val args = Bundle()
            args.putSerializable(MOVIE_KEY, movie as Serializable?)
            fragment.arguments = args
            return fragment
        }
    }
}