package com.example.mytransactions

import androidx.lifecycle.MutableLiveData
import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.domain.TransactionListUseCase
import com.example.mytransactions.presentation.list.TransactionListViewModel
import com.example.mytransactions.utils.Resource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionListViewModelTest {

    lateinit var transactionListViewModel: TransactionListViewModel

    @Mock
    private lateinit var transactionListUseCase: TransactionListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this).close()
        transactionListViewModel = TransactionListViewModel(transactionListUseCase)
    }

    @Test
    fun testSuccess() {
        val transactionList = listOfNotNull(null)
        Mockito.`when`(transactionListUseCase.getTransactions())
            .thenReturn(MutableLiveData(Resource(Resource.Status.SUCCESS, transactionList, null)))
        assert(transactionListViewModel.transactions.value?.status == Resource.Status.SUCCESS)
        assert(transactionListViewModel.transactions.value?.data.isNullOrEmpty())
    }

    @Test
    fun testError() {
        val transactionList = listOfNotNull(null)
        Mockito.`when`(transactionListUseCase.getTransactions())
            .thenReturn(MutableLiveData(Resource(Resource.Status.ERROR, transactionList, null)))
        assert(transactionListViewModel.transactions.value?.status == Resource.Status.ERROR)
        assert(transactionListViewModel.transactions.value?.data.isNullOrEmpty())
    }

    @Test
    fun testLoading() {
        val transactionList = listOfNotNull(null)
        Mockito.`when`(transactionListUseCase.getTransactions())
            .thenReturn(MutableLiveData(Resource(Resource.Status.LOADING, transactionList, null)))
        assert(transactionListViewModel.transactions.value?.status == Resource.Status.LOADING)
        assert(transactionListViewModel.transactions.value?.data.isNullOrEmpty())
    }

    @Test
    fun testSuccessWithOneEntity() {
        val transactionList = listOfNotNull(
            Transaction(1, "2021-08-31T15:47:10", "Hackett, Stamm and Kuhn", 9379.55, 0.0)
        )
        Mockito.`when`(transactionListUseCase.getTransactions())
            .thenReturn(MutableLiveData(Resource(Resource.Status.SUCCESS, transactionList, null)))
        assert(transactionListViewModel.transactions.value?.status == Resource.Status.SUCCESS)
        val transaction = transactionListViewModel.transactions.value?.data?.get(0)
        assert(transaction!!.id == 1)
        assert(transaction.transactionDate == "2021-08-31T15:47:10")
        assert(transaction.summary == "Hackett, Stamm and Kuhn")
        assert(transaction.debit == 9379.55)
        assert(transaction.credit == 0.0)
    }
}