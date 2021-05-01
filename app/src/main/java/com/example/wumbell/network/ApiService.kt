package com.example.wumbell.network


import com.example.wumbell.network.response.RepsCount
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
private const val API_KEY="1UDJYR2A4172IS44"
private const val BASE_URL="https://api.thingspeak.com"
private val okHttpLogger=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttp= OkHttpClient.Builder().addInterceptor(okHttpLogger).build()

private val retrofit= Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttp)
    .build()
interface ApiCall{
    @GET("/channels/1377253/feeds.json?api_key=$API_KEY&results=1")
    fun getCountAsync(): Deferred<RepsCount>
}
object Api{
    val retrofitService: ApiCall =retrofit.create(ApiCall::class.java)

}