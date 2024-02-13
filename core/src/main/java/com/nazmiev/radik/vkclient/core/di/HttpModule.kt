package com.nazmiev.radik.vkclient.core.di

import android.content.Context
import com.nazmiev.radik.vkclient.core.BuildConfig
import com.nazmiev.radik.vkclient.core.http.HttpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpModule {

    @Provides
    @Singleton
    @Named("VKAPI")
    fun providesBaseUrl() : String = "https://api.vk.com/method/"

    @Provides
    @Singleton
    @Named("VKAPI")
    internal fun provideOkhttpClient(): OkHttpClient {

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
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
    @Named("VKAPI")
    fun provideRetrofit(@Named("VKAPI") BASE_URL : String, @Named("VKAPI") okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    @Named("VKAPI")
    fun provideMainService(@Named("VKAPI") retrofit : Retrofit) : HttpService = retrofit.create(HttpService::class.java)

//    @Provides
//    @Singleton
//    @Named("VKAPI")
//    fun provideMainRemoteData(@Named("VKAPI") mainService : HttpService) : RemoteDataSource = RemoteDataSource(mainService)
}