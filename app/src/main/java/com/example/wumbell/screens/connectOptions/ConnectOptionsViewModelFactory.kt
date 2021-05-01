package com.example.wumbell.screens.connectOptions

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ConnectOptionsViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectOptionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ConnectOptionsViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}