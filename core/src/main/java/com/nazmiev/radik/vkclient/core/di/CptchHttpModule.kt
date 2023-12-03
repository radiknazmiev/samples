package com.nazmiev.radik.vkclient.core.di

import com.nazmiev.radik.vkclient.core.BuildConfig
import com.nazmiev.radik.vkclient.core.http.CaptchaHttpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CptchHttpModule {

    @Provides
    @Singleton
    @Named("cptch")
    fun providesBaseUrl() : String = "https://cptch.net/"

    @Provides
    @Singleton
    @Named("cptch")
    internal fun provideOkhttpClient(): OkHttpClient {

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor { chain ->
            val original = chain.request()
            var response: Response?
            val requestBuilder = original.newBuilder()

            val request = requestBuilder
                .method(original.method, original.body)
                .build()

            response = chain.proceed(request)

            response
        }

        if (BuildConfig.DEBUG) {
            client.addInterceptor(logging)
        }
        return client.build()
    }

    @Provides
    @Singleton
    @Named("cptch")
    fun provideRetrofit(@Named("cptch") BASE_URL : String, @Named("cptch") okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    @Named("cptch")
    fun provideMainService(@Named("cptch") retrofit : Retrofit) : CaptchaHttpService = retrofit.create(
        CaptchaHttpService::class.java)
}