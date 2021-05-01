package com.example.wumbell.repo


import com.example.wumbell.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository() {

    suspend fun refreshData():String?{
        val field:String?
            withContext(Dispatchers.IO){
                val received= Api.retrofitService.getCountAsync().await()
                field=received.feeds?.get(0)?.field1
            }
        return field
    }
    suspend fun isOnline():String?{
        val name:String?
        withContext(Dispatchers.IO){
            val received= Api.retrofitService.getCountAsync().await()
            name=received.channel?.let { it.name }
        }
        return name
    }

}