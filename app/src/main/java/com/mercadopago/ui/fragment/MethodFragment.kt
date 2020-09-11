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
import com.mercadopago.databinding.MethodFragmentBinding
import com.mercadopago.model.PaymentMethod
import com.mercadopago.ui.adapter.MethodAdapter
import com.mercadopago.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MethodFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>()
    val methods = ArrayList<PaymentMethod>()
    val methodsAdapter = MethodAdapter(methods) { v ->
        viewModel.showIssuers(v, methods.find { it.id == v.tag.toString() })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: MethodFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.method_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.methodsList.apply {
            hasFixedSize()
            adapter = methodsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.amount.value = "100"

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getCards().observe(viewLifecycleOwner) {
            methods.apply {
                clear()
                addAll(it)
                sortBy { it.name }
            }
            methodsAdapter.notifyDataSetChanged()
        }
    }

}