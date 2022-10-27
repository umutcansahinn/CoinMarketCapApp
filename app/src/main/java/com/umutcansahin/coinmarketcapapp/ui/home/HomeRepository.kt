package com.umutcansahin.coinmarketcapapp.ui.home

import com.umutcansahin.coinmarketcapapp.base.BaseRepository
import com.umutcansahin.coinmarketcapapp.network.ApiFactory
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiFactory: ApiFactory
) : BaseRepository() {

    suspend fun getData(
        apiKey: String,
        limit: String
    ) = safeApiRequest {
        apiFactory.getData(apiKey,limit)
    }
}