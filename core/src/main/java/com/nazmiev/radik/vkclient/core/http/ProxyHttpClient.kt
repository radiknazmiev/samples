package com.nazmiev.radik.vkclient.core.http

import com.nazmiev.radik.vkclient.core.BuildConfig
import com.nazmiev.radik.vkclient.core.db.AppDatabase
import com.nazmiev.radik.vkclient.core.db.models.ProxyList
import okhttp3.Authenticator
import okhttp3.Credentials.basic
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class ProxyHttpClient @Inject constructor(
    @Named("VKAPI") private val BASE_URL: String,
    @Named("VKAPI") private val httpService: HttpService,
    private val db: AppDatabase
) {

    private var proxy: ProxyList? = null

    suspend fun initialization(userId: Int): HttpService {
        proxy = db.proxyAccountDao().getBoundedProxy(userId)

        return if(proxy == null) {
            httpService
        } else {
            provideMainService()
        }
    }

    private fun provideMainService() : HttpService = provideRetrofit().create(HttpService::class.java)

    private fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(provideOkhttpClient())
        .build()

    private fun provideOkhttpClient(): OkHttpClient {

        var client: OkHttpClient.Builder? = null

        if (proxy?.proxyLogin == "") {
            val proxy = Proxy(
                Proxy.Type.HTTP,
                InetSocketAddress.createUnresolved(proxy?.hostname, proxy?.port!!)
            )
            val builder: OkHttpClient.Builder = OkHttpClient.Builder().proxy(proxy)
//            if (!userAgent.isEmpty()) {
//                builder.addInterceptor(UserAgentInterceptor(userAgent))
//            }
            val clientBuilder: OkHttpClient.Builder = builder
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
            client = clientBuilder
        } else {
            val proxyAuthenticator = Authenticator { route, response ->
                val credential = basic(proxy?.proxyLogin!!, proxy?.proxyPassword!!)
                if (response.request.header("Proxy-Authorization") != null) {
                    null // If we already failed with these credentials, don't retry.
                } else response.request.newBuilder().header("Proxy-Authorization", credential)
                    .build()
            }
            val proxy = Proxy(
                Proxy.Type.HTTP,
                InetSocketAddress.createUnresolved(proxy?.hostname, proxy?.port!!)
            )

            try {
                val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                    .proxy(proxy)
                    .proxyAuthenticator(proxyAuthenticator)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                client = clientBuilder
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
        }



//        val client = OkHttpClient.Builder()
//            .connectTimeout(20, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client?.addInterceptor { chain ->
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
            client?.addInterceptor(logging)
        }
        return client?.build()!!
    }
}