package com.mercadopago.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.mercadopago.R
import com.mercadopago.databinding.AmountFragmentBinding
import com.mercadopago.databinding.CardFragmentBinding
import com.mercadopago.ui.viewmodel.MainViewModel

class CardFragment : Fragment() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: CardFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.card_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.amout.observe(viewLifecycleOwner) {
            Log.e(">>>", "Received update")
        }
    }

}