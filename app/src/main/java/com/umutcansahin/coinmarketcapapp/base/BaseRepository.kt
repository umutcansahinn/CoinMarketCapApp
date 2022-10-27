package com.umutcansahin.coinmarketcapapp.base

import com.google.gson.Gson
import com.umutcansahin.coinmarketcapapp.model.errorResponce.ErrorResponse
import com.umutcansahin.coinmarketcapapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Error

abstract class BaseRepository {

    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResult.Success(apiRequest.invoke())
            }catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        NetworkResult.Error(false,errorBodyParser(throwable.response()?.errorBody()?.string()))
                    }
                    else -> {
                        NetworkResult.Error(true,throwable.localizedMessage)
                    }
                }
            }
        }
    }

    private fun errorBodyParser(error: String?): String {
        error?.let {
            return try {
                val errorResponce = Gson().fromJson(error,ErrorResponse::class.java)
                val errorMessage = errorResponce.status?.errorMessage
                errorMessage ?: "Bilinmeyen bir hata oluştu."
            }catch (e: Exception) {
                "Bilinmeyen bir hata oluştu."
            }
        }
        return "Bilinmeyen bir hata oluştu."
    }
}