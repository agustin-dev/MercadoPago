package com.mercadopago.model

data class PaymentMethod(
    val id: String,
    val name: String,
    val type: String,
    val status: String,
    val thumbnail: String,
    val min_allowed_amount: Float,
    val max_allowed_amount: Float
)