package com.example.sportwisdom.data.di

import android.content.Context
import androidx.work.WorkManager
import com.example.sportwisdom.data.local.datasource.favorite.FavoriteLocalDataSource
import com.example.sportwisdom.data.local.datasource.favorite.FavoriteLocalDataSourceImpl
import com.example.sportwisdom.data.local.datasource.home.HomeLocalDataSource
import com.example.sportwisdom.data.local.datasource.home.HomeLocalDataSourceImpl
import com.example.sportwisdom.data.local.datasource.schedule.ScheduleLocalDataSource
import com.example.sportwisdom.data.local.datasource.schedule.ScheduleLocalDataSourceImpl
import com.example.sportwisdom.data.local.datasource.search.SearchLocalDataSource
import com.example.sportwisdom.data.local.datasource.search.SearchLocalDataSourceImpl
import com.example.sportwisdom.data.local.room.SportWisdomDao
import com.example.sportwisdom.data.remote.apiservice.SportApiService
import com.example.sportwisdom.data.remote.datasource.home.HomeRemoteDataSource
import com.example.sportwisdom.data.remote.datasource.home.HomeRemoteDataSourceImpl
import com.example.sportwisdom.data.remote.datasource.search.SearchRemoteDataSource
import com.example.sportwisdom.data.remote.datasource.search.SearchRemoteDataSourceImpl
import com.example.sportwisdom.data.repositoryimpl.FavoriteRepositoryImpl
import com.example.sportwisdom.data.repositoryimpl.HomeRepositoryImpl
import com.example.sportwisdom.data.repositoryimpl.ScheduleRepositoryImpl
import com.example.sportwisdom.data.repositoryimpl.SearchRepositoryImpl
import com.example.sportwisdom.domain.repository.FavoriteRepository
import com.example.sportwisdom.domain.repository.HomeRepository
import com.example.sportwisdom.domain.repository.ScheduleRepository
import com.example.sportwisdom.domain.repository.SearchRepository
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
  fun providesHomeLocalDataSource(sportWisdomDao: SportWisdomDao, workManager: WorkManager): HomeLocalDataSource {
    return HomeLocalDataSourceImpl(sportWisdomDao, workManager)
  }

  @Singleton
  @Provides
  fun provideScheduleRepository(localDataSource: ScheduleLocalDataSource): ScheduleRepository = ScheduleRepositoryImpl(localDataSource)

  @Singleton
  @Provides
  fun provideScheduleLocalDataSource(sportWisdomDao: SportWisdomDao, workManager: WorkManager): ScheduleLocalDataSource {
    return ScheduleLocalDataSourceImpl(sportWisdomDao, workManager)
  }

  @Singleton
  @Provides
  fun provideSearchRepository(remoteDataSource: SearchRemoteDataSource, localDataSource: SearchLocalDataSource): SearchRepository {
    return SearchRepositoryImpl(remoteDataSource, localDataSource)
  }

  @Singleton
  @Provides
  fun provideSearchRemoteDataSource(apiService: SportApiService): SearchRemoteDataSource = SearchRemoteDataSourceImpl(apiService)

  @Singleton
  @Provides
  fun provideSearchLocalDataSource(dao: SportWisdomDao): SearchLocalDataSource = SearchLocalDataSourceImpl(dao)

  @Singleton
  @Provides
  fun provideFavoriteLocalDataSource(dao: SportWisdomDao): FavoriteLocalDataSource = FavoriteLocalDataSourceImpl(dao)

  @Singleton
  @Provides
  fun provideFavoriteRepository(localDataSource: FavoriteLocalDataSource): FavoriteRepository = FavoriteRepositoryImpl(localDataSource)

  @Singleton
  @Provides
  fun provideWorkManager(@ApplicationContext context: Context) = WorkManager.getInstance(context)
}