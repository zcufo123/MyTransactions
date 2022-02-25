package com.example.mytransactions.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.databinding.TransactionDetailFragmentBinding
import com.example.mytransactions.utils.Constant
import com.example.mytransactions.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class TransactionDetailFragment : Fragment() {

    private var binding: TransactionDetailFragmentBinding by autoCleared()
    private val viewModel: TransactionDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TransactionDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(Constant.ID)?.let { setupObservers(it) }
    }

    private fun setupObservers(id: Int) {
        viewModel.find(id).observe(viewLifecycleOwner) {
            bindEntity(it)
        }
    }

    private fun bindEntity(transaction: Transaction) {
        binding.transactionSummary.text = transaction.summary
        binding.transactionDate.text = transaction.transactionDate
        val dec = DecimalFormat("0.00")
        binding.debit.text = dec.format(transaction.debit)
        binding.credit.text = dec.format(transaction.credit)
    }
}
