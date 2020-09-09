package com.mercadopago.net

import com.mercadopago.model.Bank
import com.mercadopago.model.PaymentMethod
import retrofit2.http.GET

interface ApiService {

    @GET("")
    suspend fun getCards(): List<PaymentMethod>

    @GET("card_issuers")
    suspend fun getBanks(): List<Bank>

    @GET("lookup.php")
    suspend fun getInstallments(): List<Int>
}