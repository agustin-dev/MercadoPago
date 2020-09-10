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
import com.mercadopago.databinding.InstallmentsFragmentBinding
import com.mercadopago.model.Installment
import com.mercadopago.model.PayerCost
import com.mercadopago.ui.adapter.InstallmentAdapter
import com.mercadopago.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstallmentsFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>()
    val payerCost = ArrayList<PayerCost>()
    val installmentAdapter = InstallmentAdapter(payerCost) {
        viewModel.nextStep(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: InstallmentsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.installments_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.installmentsList.apply {
            hasFixedSize()
            adapter = installmentAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getInstallments().observe(viewLifecycleOwner) {
            payerCost.clear()
            payerCost.addAll(it.first().payer_costs)
            installmentAdapter.notifyDataSetChanged()
        }
    }

}