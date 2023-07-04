package com.vcco.a20230704_vcco_nycschools.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.vcco.a20230704_vcco_nycschools.BuildConfig
import com.vcco.a20230704_vcco_nycschools.network.api.NYCSchoolsAPI
import com.vcco.a20230704_vcco_nycschools.network.manager.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    /**
     * Provides a unique instance of OKHttpClient, to debug networkCalls
     * @return OkHttpClient *Unique instance*
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loginInterceptor = HttpLoggingInterceptor()
        loginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .build()
    }

    /**
     * Provides a unique instance of Retrofit to Network call, with Gson to convert the response
     * @param OkHttpClient *Injected*
     * @return retrofit client *Unique instance*
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    /**
     * Provide unique instance Api Service to be called on Api Implementation
     * @param retrofit *Injected*
     * @return SchoolAPiService *Unique instance*
     */
    @Provides
    @Singleton
    fun provideSchoolApiService(retrofit: Retrofit) =
        retrofit.create(NYCSchoolsAPI::class.java)

    /**
     * Provide unique instance of Network Manager
     */
    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context) =
        context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

    /**
     * Provide unique instance of Custom Network Manager for the app
     */
    @Provides
    @Singleton
    fun provideNetworkManager(connectivityManager: ConnectivityManager) =
        NetworkManager(connectivityManager)
}