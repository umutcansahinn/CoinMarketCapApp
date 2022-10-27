package com.umutcansahin.coinmarketcapapp.ui.detail

import com.umutcansahin.coinmarketcapapp.base.BaseRepository
import com.umutcansahin.coinmarketcapapp.network.ApiFactory
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val apiFactory: ApiFactory
) : BaseRepository() {

    suspend fun getDetail(
        apiKey: String,
        symbol: String
    ) = safeApiRequest {
        apiFactory.getDetail(apiKey, symbol)
    }
}