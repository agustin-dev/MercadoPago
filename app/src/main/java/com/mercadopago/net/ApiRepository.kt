package com.mercadopago.net

import com.mercadopago.model.Issuer
import com.mercadopago.model.Installment
import com.mercadopago.model.PaymentMethod
import javax.inject.Inject

class ApiRepository @Inject constructor(
    val apiService: ApiService
) {

    suspend fun getMethods(): List<PaymentMethod> {
        return apiService.getMethods()
    }

    suspend fun getIssuers(methodId: String): List<Issuer> {
        return apiService.getIssuers(methodId)
    }

    suspend fun getInstallments(amount: String, methodId: String, issuer: String): List<Installment> {
        return apiService.getInstallments(amount, methodId, issuer)
    }
}