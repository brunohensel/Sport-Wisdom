package com.example.sportwisdom.di

import android.content.Context
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.home.data.HomeRepository
import com.example.sportwisdom.features.home.data.HomeRepositoryImpl
import com.example.sportwisdom.features.home.data.datasource.local.HomeLocalDataSource
import com.example.sportwisdom.features.home.data.datasource.local.HomeLocalDataSourceImpl
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSource
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideHomeRepository(remoteDataSource: HomeRemoteDataSource, localDataSource: HomeLocalDataSource): HomeRepository {
    return HomeRepositoryImpl(remoteDataSource, localDataSource)
  }

  @Singleton
  @Provides
  fun provideHomeRemoteDataSource(apiService: SportApiService): HomeRemoteDataSource = HomeRemoteDataSourceImpl(apiService)

  @Singleton
  @Provides
  fun providesHomeLocalDataSource(sportWisdomDao: SportWisdomDao, @ApplicationContext context: Context): HomeLocalDataSource{
    return HomeLocalDataSourceImpl(sportWisdomDao, context)
  }
}