package com.example.wumbell.screens.timer

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wumbell.network.Api
import com.example.wumbell.repo.Repository
import kotlinx.coroutines.*

class TimerViewModel(application: Application): AndroidViewModel(application) {


    private val _reps = MutableLiveData<String?>()
    val reps: LiveData<String?>
        get() = _reps
    private val _timer = MutableLiveData<String?>()
    val timer: LiveData<String?>
        get() = _timer
    private val repo=Repository()
    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private suspend fun getCurrentReps() {
        val field:String?
        withContext(Dispatchers.IO){
                field= repo.refreshData()
        }
        withContext(Dispatchers.Main){
            if(field!=null) {
                _reps.value=field
            }
        }
    }
    val countDownTimer = object : CountDownTimer(31000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val secondsInMilli: Long = 1000
            val elapsedSeconds = millisUntilFinished / secondsInMilli
            _timer.value= "$elapsedSeconds sec"
                uiScope.launch { getCurrentReps() }
        }
        override fun onFinish() {
            _timer.value="Completed!!"
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}