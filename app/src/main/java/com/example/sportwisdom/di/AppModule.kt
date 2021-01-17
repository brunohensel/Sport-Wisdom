package com.example.sportwisdom.di

import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.dashboard.data.DashboardRepository
import com.example.sportwisdom.features.dashboard.data.DashboardRepositoryImpl
import com.example.sportwisdom.features.dashboard.data.datasource.remote.DashboardRemoteDataSource
import com.example.sportwisdom.features.dashboard.data.datasource.remote.DashboardRemoteDataSourceImpl
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
  fun provideDashboardRepository(remoteDataSource: DashboardRemoteDataSource): DashboardRepository = DashboardRepositoryImpl(remoteDataSource)

  @Singleton
  @Provides
  fun provideDashboardRemoteDataSource(apiService: SportApiService): DashboardRemoteDataSource = DashboardRemoteDataSourceImpl(apiService)
}