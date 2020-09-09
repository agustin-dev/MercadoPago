package com.mercadopago.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.mercadopago.R
import com.mercadopago.databinding.MethodFragmentBinding
import com.mercadopago.model.PaymentMethod
import com.mercadopago.ui.adapter.MethodAdapter
import com.mercadopago.ui.viewmodel.MainViewModel

class MethodFragment : Fragment() {

    val viewModel by viewModels<MainViewModel>()
    val cards = ArrayList<PaymentMethod>()
    val adapter = MethodAdapter(cards)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: MethodFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.method_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.cardList.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getCards().observe(viewLifecycleOwner) {
            cards.clear()
            cards.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

}