package com.example.mytransactions

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.mytransactions.data.model.Transaction
import com.example.mytransactions.domain.TransactionDetailUseCase
import com.example.mytransactions.extension.launchFragmentInHiltContainer
import com.example.mytransactions.presentation.detail.TransactionDetailFragment
import com.example.mytransactions.utils.Constant
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import javax.inject.Inject


@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class TransactionDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Mock
    @Inject
    lateinit var transactionDetailUseCase: TransactionDetailUseCase

    @Before
    fun setup() {
        transactionDetailUseCase = Mockito.mock(TransactionDetailUseCase::class.java)
        hiltRule.inject()
    }

    @Test
    fun testSuccess() {
        val transaction =
            Transaction(1, "2021-08-31T15:47:10", "Hackett, Stamm and Kuhn", 9379.55, 0.0)
        Mockito.`when`(transactionDetailUseCase.getTransactionDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(transaction))
        val fragmentArgs = bundleOf(Constant.ID to 0)
        launchFragmentInHiltContainer<TransactionDetailFragment>(fragmentArgs)
        onView(withId(R.id.transaction_summary)).check(ViewAssertions.matches(withText("Hackett, Stamm and Kuhn")))
        onView(withId(R.id.transaction_date)).check(ViewAssertions.matches(withText("2021-08-31T15:47:10")))
        onView(withId(R.id.debit)).check(ViewAssertions.matches(withText("9379.55")))
        onView(withId(R.id.credit)).check(ViewAssertions.matches(withText("0.00")))
    }

}