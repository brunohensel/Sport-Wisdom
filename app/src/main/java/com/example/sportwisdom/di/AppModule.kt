package com.example.sportwisdom.di

import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.home.data.HomeRepository
import com.example.sportwisdom.features.home.data.HomeRepositoryImpl
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSource
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideDashboardRepository(remoteDataSource: HomeRemoteDataSource): HomeRepository = HomeRepositoryImpl(remoteDataSource)

  @Singleton
  @Provides
  fun provideDashboardRemoteDataSource(apiService: SportApiService): HomeRemoteDataSource = HomeRemoteDataSourceImpl(apiService)
}