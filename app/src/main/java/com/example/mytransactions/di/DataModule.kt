package com.example.mytransactions.di

import android.content.Context
import com.example.mytransactions.data.local.AppDatabase
import com.example.mytransactions.data.local.TransactionDao
import com.example.mytransactions.data.remote.TransactionRemoteDataSource
import com.example.mytransactions.data.remote.TransactionService
import com.example.mytransactions.data.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(transactionService: TransactionService) =
        TransactionRemoteDataSource(transactionService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.dao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: TransactionRemoteDataSource,
        localDataSource: TransactionDao
    ) = TransactionRepository(remoteDataSource, localDataSource)

}