package com.umutcansahin.coinmarketcapapp.model.home

data class Platform(
    val id: Int,
    val name: String,
    val slug: String,
    val symbol: String,
    val token_address: String
)