package com.vcco.a20230704_vcco_nycschools.di.module

import com.vcco.a20230704_vcco_nycschools.BuildConfig
import com.vcco.a20230704_vcco_nycschools.network.api.NYCSchoolsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    //Provides a unique instance of OKHttpClient, to debug networkCalls
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loginInterceptor = HttpLoggingInterceptor()
        loginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .build()
    }

    //Provides a unique instance of Retrofit to Network call, with Gson to convert the response
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    //Provide unique Api Service to be called on Api Implementation
    @Provides
    @Singleton
    fun provideSchoolApiService(retrofit: Retrofit) =
        retrofit.create(NYCSchoolsAPI::class.java)

}