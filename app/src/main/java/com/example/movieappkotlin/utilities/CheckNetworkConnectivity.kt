package com.example.movieappkotlin.utilities

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.movieappkotlin.constants.MovieConstants

class CheckNetworkConnectivity {
    fun check(context : Context): Boolean{
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            return true
        }
        return false
    }
}