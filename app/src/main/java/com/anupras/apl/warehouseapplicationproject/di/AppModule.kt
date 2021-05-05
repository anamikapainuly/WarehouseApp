package com.anupras.apl.warehouseapplicationproject.di

import com.anupras.apl.warehouseapplicationproject.BuildConfig
import com.anupras.apl.warehouseapplicationproject.api.WarehouseApiService
import com.anupras.apl.warehouseapplicationproject.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    // Define the interceptor, add authentication headers
    private val interceptor = Interceptor { chain ->
        val newRequest: Request =
            chain.request().newBuilder().addHeader("Ocp-Apim-Subscription-Key", BuildConfig.SUBSCRIPTION_KEY).build()
        chain.proceed(newRequest)
    }
    private val builder = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(builder)
            .baseUrl(Constants.HTTP_URL_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWarehouseApi(retrofit: Retrofit): WarehouseApiService =
        retrofit.create(WarehouseApiService::class.java)

}