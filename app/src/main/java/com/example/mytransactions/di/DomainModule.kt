package com.example.mytransactions.di

import com.example.mytransactions.data.repository.TransactionRepository
import com.example.mytransactions.domain.TransactionListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun provideTransactionListUseCase(entityRepository: TransactionRepository) =
        TransactionListUseCase(entityRepository)

}