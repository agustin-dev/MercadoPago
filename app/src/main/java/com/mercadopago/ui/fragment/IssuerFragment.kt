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
import com.mercadopago.databinding.IssuerFragmentBinding
import com.mercadopago.model.Issuer
import com.mercadopago.ui.adapter.IssuerAdapter
import com.mercadopago.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssuerFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>()
    val issuers = ArrayList<Issuer>()
    val issuerAdapter = IssuerAdapter(issuers) { v ->
        viewModel.showInstallments(v, issuers.find { it.id == v.tag.toString() })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: IssuerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.issuer_fragment, container, false)
        binding.apply {
            viewmodel = this@IssuerFragment.viewModel
            binding.lifecycleOwner = this@IssuerFragment
            issuersList.apply {
                hasFixedSize()
                adapter = issuerAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        viewModel.getIssuers().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                issuers.apply {
                    clear()
                    addAll(it)
                    sortBy { it.name }
                }
                issuerAdapter.notifyDataSetChanged()
            } else {
                viewModel.showInstallments(requireView(), null)
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}