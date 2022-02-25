package com.example.mytransactions.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytransactions.databinding.TransactionListFragmentBinding
import com.example.mytransactions.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionListFragment : Fragment() {

    private var binding: TransactionListFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TransactionListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
