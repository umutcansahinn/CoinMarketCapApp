package com.umutcansahin.coinmarketcapapp.model.errorResponce

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Status?
)