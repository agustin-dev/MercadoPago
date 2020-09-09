package com.mercadopago.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercadopago.databinding.MethodItemBinding
import com.mercadopago.model.PaymentMethod

class MethodAdapter(val paymentMethods: List<PaymentMethod>) :
    RecyclerView.Adapter<MethodAdapter.ViewHolder>() {

    class ViewHolder(val bind: MethodItemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(paymentMethod: PaymentMethod) {
            bind.method = paymentMethod
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MethodItemBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = paymentMethods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(paymentMethods[position])
    }
}