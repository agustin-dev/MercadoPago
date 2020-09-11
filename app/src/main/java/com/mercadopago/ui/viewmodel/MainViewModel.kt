package com.mercadopago.ui.viewmodel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.Navigation
import com.mercadopago.R
import com.mercadopago.model.*
import com.mercadopago.net.ApiRepository
import com.mercadopago.util.MaskWatcher
import kotlinx.coroutines.Dispatchers

class MainViewModel @ViewModelInject constructor(
    val apiRepository: ApiRepository
) : ViewModel() {

    val amount: MutableLiveData<String> = MutableLiveData()
    val amountMask: MaskWatcher = MaskWatcher()
    var paymentMethod: PaymentMethod? = null
    var issuer: Issuer? = null
    var payerCost: PayerCost? = null
    var card: Card = Card()

    fun nextStep(view: View) {
        when (view.id) {
            R.id.btn_amount -> {
                Navigation.findNavController(view).navigate(R.id.action_amountFragment_to_cardFragment)
            }
            R.id.input_data_btn_ok -> {
                Navigation.findNavController(view).navigate(R.id.action_inputDataFragment_to_summaryFragment)
            }
            else -> {
                amount.value = null
                paymentMethod = null
                issuer = null
                payerCost = null
                card = Card()
                Navigation.findNavController(view).navigate(R.id.amountFragment)
            }
        }
    }

    fun showIssuers(v: View, m: PaymentMethod?) {
        paymentMethod = m
        Navigation.findNavController(v).navigate(R.id.action_cardFragment_to_issuerFragment)
    }

    fun showInstallments(v: View, i: Issuer?) {
        issuer = i
        if (i == null) {
            Navigation.findNavController(v).apply {
                popBackStack()
                navigate(R.id.action_cardFragment_to_installmentsFragment)
            }
        } else {
            Navigation.findNavController(v).navigate(R.id.action_issuerFragment_to_installmentsFragment)
        }
    }

    fun showInputData(v: View, p: PayerCost?) {
        payerCost = p
        Navigation.findNavController(v).navigate(R.id.action_installmentsFragment_to_inputDataFragment)
    }

    fun getCards() = liveData(Dispatchers.IO) {
        emit(apiRepository.getMethods())
    }

    fun getIssuers() = liveData {
        emit(apiRepository.getIssuers(paymentMethod?.id ?: ""))
    }

    fun getInstallments() = liveData {
        emit(apiRepository.getInstallments(
            amount.value ?: "",
            paymentMethod?.id ?: "",
        issuer?.id ?: ""))
    }
}