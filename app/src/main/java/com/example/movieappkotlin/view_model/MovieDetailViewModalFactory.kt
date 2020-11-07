package com.example.movieappkotlin.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailViewModelFactory(private val mApplication: Application, private val mParam: String?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModal(mApplication, mParam) as T
    }
}
