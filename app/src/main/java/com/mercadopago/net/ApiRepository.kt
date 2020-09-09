package com.mercadopago.net

import com.mercadopago.model.PaymentMethod
import javax.inject.Inject

class ApiRepository @Inject constructor(
    val apiService: ApiService
) {

    suspend fun getCards(): List<PaymentMethod> {
        return apiService.getCards()
    }
}