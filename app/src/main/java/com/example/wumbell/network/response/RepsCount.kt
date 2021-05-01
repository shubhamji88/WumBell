package com.example.wumbell.network.response


import com.google.gson.annotations.SerializedName

data class RepsCount(
    @SerializedName("channel")
    val channel: Channel?,
    @SerializedName("feeds")
    val feeds: List<Feed>?
)