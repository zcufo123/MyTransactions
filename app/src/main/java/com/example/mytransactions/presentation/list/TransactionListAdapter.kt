package com.example.mytransactions.presentation.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.databinding.ItemBinding
import java.text.DecimalFormat

class TransactionListAdapter(private val listener: ItemListener) : RecyclerView.Adapter<MainViewHolder>() {

    interface ItemListener {
        fun onClicked(id: Int)
    }

    private val items = ArrayList<Transaction>()

    fun setItems(items: ArrayList<Transaction>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = holder.bind(items[position])
}

class MainViewHolder(private val itemBinding: ItemBinding, private val listener: TransactionListAdapter.ItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var transaction: Transaction

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Transaction) {
        transaction = item
        itemBinding.summary.text = transaction.summary
        itemBinding.transactionDate.text = transaction.transactionDate
        val dec = DecimalFormat("0.00")
        itemBinding.debit.text = dec.format(transaction.debit)
        itemBinding.credit.text = dec.format(transaction.credit)
    }

    override fun onClick(v: View?) {
        listener.onClicked(transaction.id)
    }
}

