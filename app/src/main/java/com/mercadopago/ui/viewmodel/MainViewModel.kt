package com.mercadopago.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.mercadopago.R

class MainViewModel : ViewModel() {

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
}