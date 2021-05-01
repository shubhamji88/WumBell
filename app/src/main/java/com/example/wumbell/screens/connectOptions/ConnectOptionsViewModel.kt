package com.example.wumbell.screens.connectOptions

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wumbell.repo.Repository
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.UnknownHostException

class ConnectOptionsViewModel( application: Application)  : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val applic=application
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _navigateToMainPage = MutableLiveData<String?>()
    val navigateToMainPage: LiveData<String?>
        get() = _navigateToMainPage
    val repo=Repository()
    fun checkOnline(){
        uiScope.launch {
            try {
                val result=repo.isOnline()
                if(!result.isNullOrEmpty())
                {
                    withContext(Dispatchers.Main){
                        _navigateToMainPage.value=result
                    }
                }
            }catch (e: UnknownHostException){
            withContext(Dispatchers.Main) {
                Toast.makeText(applic,"Connect to internet", Toast.LENGTH_LONG).show()
            }
        }
        catch (e:Exception){
            Toast.makeText(applic,"$e", Toast.LENGTH_SHORT).show()
        }

        }
    }
    fun doneNavigating() {
        _navigateToMainPage.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}