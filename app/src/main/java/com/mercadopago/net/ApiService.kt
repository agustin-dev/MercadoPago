package com.mercadopago.net

import com.mercadopago.BuildConfig
import com.mercadopago.model.Bank
import com.mercadopago.model.Card
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("filter.php?g=Cocktail_glass")
    suspend fun getCards(@Query(BuildConfig.API_KEY) key: String): List<Card>

    @GET("lookup.php")
    suspend fun getBanks(@Query(BuildConfig.API_KEY) key: String): List<Bank>

    @GET("lookup.php")
    suspend fun getInstallments(@Query("i") id: Int): List<Int>
}