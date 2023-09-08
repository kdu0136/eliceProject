package com.example.eliceproject.data.remote

import com.google.gson.annotations.SerializedName

data class ResResult(
    @SerializedName("status") val status: String,
    @SerializedName("reason") val reason: String?,
)
