package com.mercadopago.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.mercadopago.R
import com.mercadopago.databinding.InputDataFragmentBinding
import com.mercadopago.databinding.InstallmentsFragmentBinding
import com.mercadopago.model.Card
import com.mercadopago.model.Installment
import com.mercadopago.model.PayerCost
import com.mercadopago.ui.adapter.InstallmentAdapter
import com.mercadopago.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputDataFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: InputDataFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.input_data_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}