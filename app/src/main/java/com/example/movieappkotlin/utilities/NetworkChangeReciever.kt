package com.example.movieappkotlin.utilities

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log


abstract class NetworkChangeReceiver : BroadcastReceiver() {
    var online:Boolean = true
    override fun onReceive(context: Context, intent: Intent?) {
        try {
            if (isOnline(context)) {
                if(!online)
                    DoWhat()
                Log.e("Sumit", "Online Connect Intenet ")
                online = true
            } else {
                online = false
                Log.e("sumit", "Conectivity Failure !!! ")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    abstract fun DoWhat()


    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }
}