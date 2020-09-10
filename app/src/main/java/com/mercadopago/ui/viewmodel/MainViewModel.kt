package com.mercadopago.ui.viewmodel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.Navigation
import com.mercadopago.R
import com.mercadopago.model.Issuer
import com.mercadopago.model.PaymentMethod
import com.mercadopago.net.ApiRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel @ViewModelInject constructor(
    val apiRepository: ApiRepository
) : ViewModel() {

    val amout: MutableLiveData<String> = MutableLiveData()
    var paymentMethod: PaymentMethod? = null
    var issuer: Issuer? = null

    fun nextStep(view: View) {
        when (view.id) {
            R.id.btn_amount -> {
                Navigation.findNavController(view).navigate(R.id.action_amountFragment_to_cardFragment)
            }
            else -> {
                Navigation.findNavController(view).navigateUp()
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
            Navigation.findNavController(v).popBackStack(R.id.cardFragment, true)
        }
        Navigation.findNavController(v).navigate(R.id.action_issuerFragment_to_installmentsFragment)
    }

    fun getCards() = liveData(Dispatchers.IO) {
        emit(apiRepository.getMethods())
    }

    fun getIssuers() = liveData {
        emit(apiRepository.getIssuers(paymentMethod?.id ?: ""))
    }

    fun getInstallments() = liveData {
        emit(apiRepository.getInstallments(
            amout.value ?: "",
            paymentMethod?.id ?: "",
        issuer?.id ?: ""))
    }
}