package com.example.sportwisdom.di

import com.example.sportwisdom.BuildConfig
import com.example.sportwisdom.features.apiservice.SportApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

  private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

  @Singleton
  @Provides
  fun provideHttpLog(): HttpLoggingInterceptor {
    val log = HttpLoggingInterceptor()
    log.level = HttpLoggingInterceptor.Level.BODY
    return log
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(log: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor { chain ->
        chain.proceed(chain.request().newBuilder().build())
      }
      .addInterceptor(log)
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .build()
  }

  @Singleton
  @Provides
  fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(client)
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Singleton
  @Provides
  fun provideSportApi(retrofit: Retrofit): SportApiService {
    return retrofit.create(SportApiService::class.java)
  }
}