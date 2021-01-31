package com.example.sportwisdom.di

import android.content.Context
import androidx.work.WorkManager
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.favorite.data.FavoriteRepository
import com.example.sportwisdom.features.favorite.data.FavoriteRepositoryImpl
import com.example.sportwisdom.features.favorite.data.source.FavoriteLocalDataSource
import com.example.sportwisdom.features.favorite.data.source.FavoriteLocalDataSourceImpl
import com.example.sportwisdom.features.home.data.HomeRepository
import com.example.sportwisdom.features.home.data.HomeRepositoryImpl
import com.example.sportwisdom.features.home.data.datasource.local.HomeLocalDataSource
import com.example.sportwisdom.features.home.data.datasource.local.HomeLocalDataSourceImpl
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSource
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSourceImpl
import com.example.sportwisdom.features.schedule.data.ScheduleRepository
import com.example.sportwisdom.features.schedule.data.ScheduleRepositoryImpl
import com.example.sportwisdom.features.schedule.data.source.ScheduleLocalDataSource
import com.example.sportwisdom.features.schedule.data.source.ScheduleLocalDataSourceImpl
import com.example.sportwisdom.features.search.data.SearchRepository
import com.example.sportwisdom.features.search.data.SearchRepositoryImpl
import com.example.sportwisdom.features.search.data.source.local.SearchLocalDataSource
import com.example.sportwisdom.features.search.data.source.local.SearchLocalDataSourceImpl
import com.example.sportwisdom.features.search.data.source.remote.SearchRemoteDataSource
import com.example.sportwisdom.features.search.data.source.remote.SearchRemoteDataSourceImpl
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