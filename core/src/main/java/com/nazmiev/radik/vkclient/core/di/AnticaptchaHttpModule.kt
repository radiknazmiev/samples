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
class AnticaptchaHttpModule {

    @Provides
    @Singleton
    @Named("anticaptcha")
    fun providesBaseUrl() : String = "https://anti-captcha.com/"

    @Provides
    @Singleton
    @Named("anticaptcha")
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
    @Named("anticaptcha")
    fun provideRetrofit(@Named("anticaptcha") BASE_URL : String, @Named("anticaptcha") okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    @Named("anticaptcha")
    fun provideMainService(@Named("anticaptcha") retrofit : Retrofit) : CaptchaHttpService = retrofit.create(
        CaptchaHttpService::class.java)
}