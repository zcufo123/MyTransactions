package com.example.mytransactions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.domain.TransactionDetailUseCase
import com.example.mytransactions.presentation.detail.TransactionDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionDetailViewModelTest {
    lateinit var transactionDetailVieModel: TransactionDetailViewModel

    @Mock
    private lateinit var transactionDetailUseCase: TransactionDetailUseCase

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this).close()
        transactionDetailVieModel = TransactionDetailViewModel(transactionDetailUseCase)
    }

    @Test
    fun testNothing() {
        Mockito.`when`(transactionDetailUseCase.getTransactionDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(null))
        val entity = transactionDetailVieModel.find(0).value
        assert(entity == null)
    }

    @Test
    fun testSuccessWithOne() {
        val transaction =
            Transaction(1, "2021-08-31T15:47:10", "Hackett, Stamm and Kuhn", 9379.55, 0.0)
        Mockito.`when`(transactionDetailUseCase.getTransactionDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(transaction))
        val result = transactionDetailVieModel.find(0).value
        assert(result!!.id == 1)
        assert(result.transactionDate == "2021-08-31T15:47:10")
        assert(result.summary == "Hackett, Stamm and Kuhn")
        assert(result.debit == 9379.55)
        assert(result.credit == 0.0)
    }
}
