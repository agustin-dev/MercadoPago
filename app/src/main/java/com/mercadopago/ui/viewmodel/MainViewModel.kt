package com.mercadopago.ui.viewmodel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.Navigation
import com.mercadopago.R
import com.mercadopago.net.ApiRepository

class MainViewModel @ViewModelInject constructor(
    val apiRepository: ApiRepository
) : ViewModel() {

    val amout: MutableLiveData<String> = MutableLiveData()

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

    fun getCards() = liveData {
        emit(apiRepository.getCards())
    }
}