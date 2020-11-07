package com.example.movieappkotlin.data_source

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.movieappkotlin.data_source.db.DatabaseClient
import java.util.ArrayList


import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.movieappkotlin.constants.MovieConstants
import com.example.movieappkotlin.model.Movie
import com.example.movieappkotlin.network.VolleySingleton
import com.example.movieappkotlin.response.MovieResponse
import com.google.gson.GsonBuilder

class MovieOverviewDataSource {
  fun fetchfromRoom(application: Application,movieLiveData: MutableLiveData<ArrayList<Movie>>) {
        Toast.makeText(application, "In rooom", Toast.LENGTH_LONG).show()
        val thread = Thread {
            val movies: List<Movie> =
                DatabaseClient.getInstance(application)!!.appDatabase.movieDao()!!.all
            var movieList: ArrayList<Movie>  = ArrayList<Movie>()
//            for (movieEntity in movies) {
//                val repo = Movie()
//                repo.id = movieEntity.id
//                repo.title = movieEntity.title
//                repo.overview = movieEntity.overview
//                repo.backdrop_path = movieEntity.backdrop_path
//                repo.poster_path = movieEntity.poster_path
//                movieList.add(repo)
//            }
            movieList.addAll(movies)
            movieLiveData.postValue(movieList)
        }
        thread.start()
    }



   fun fetchfromServer(application: Application,movieLiveData: MutableLiveData<ArrayList<Movie>>) {
        Toast.makeText(application, "In server", Toast.LENGTH_LONG).show()
        var movieList: ArrayList<Movie> = ArrayList<Movie>()
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, MovieConstants.POPULAR_URL_MOVIE.toString() + "1", null,
            { response ->
                try {
                    if (response != null) {
                        val gson = GsonBuilder().create()
                        val movieResponse: MovieResponse = gson.fromJson(
                            response.toString(),
                            MovieResponse::class.java
                        )
                        movieList.addAll(movieResponse.movies!!)
                        movieLiveData.value = movieResponse.movies as ArrayList<Movie>
                        saveTask(application,movieList)
                    }
                } catch (e: Exception) {
                    Log.e("error", e.toString())
                    e.printStackTrace()
                }
            }
        ) { }
        jsonObjectRequest.setShouldCache(false)
        VolleySingleton.getInstance(application).requestQueue.add(jsonObjectRequest)
    }

    private fun saveTask(application: Application,movieList : ArrayList<Movie>) {
        class SaveTask :
            AsyncTask<Void?, Void?, Void?>() {
            protected override fun doInBackground(vararg params: Void?): Void? {

                //creating a task
                DatabaseClient.getInstance(application)!!.appDatabase.movieDao()!!.delete()
                for (i in movieList.indices) {
                    val movieEntity= Movie()
                    movieEntity.id = (movieList[i].id)
                    movieEntity.overview = (movieList[i].overview)
                    movieEntity.backdrop_path = (movieList[i].backdrop_path)
                    movieEntity.poster_path = (movieList[i].poster_path)
                    movieEntity.title = (movieList[i].title)
                    DatabaseClient.getInstance(application)!!.appDatabase.movieDao()!!
                        .insert(movieEntity)
                }
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                Toast.makeText(application, "Saved", Toast.LENGTH_LONG).show()
            }
        }

        val st = SaveTask()
        st.execute()
    }
}
