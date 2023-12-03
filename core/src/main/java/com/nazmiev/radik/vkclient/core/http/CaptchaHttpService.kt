package com.nazmiev.radik.vkclient.core.http

import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.QueryMap

interface CaptchaHttpService {

    @Multipart
    @POST("in.php")
    suspend fun setCaptcha(
        @Part("body") body: RequestBody,
        @Part("key") key: RequestBody,
        @Part("language") language: RequestBody,
        @Part("method") method: RequestBody,
        @Part("numeric") numeric: RequestBody,
        @Part("soft_id") softId: RequestBody
    ): String

    @GET("res.php")
    suspend fun getResolvedCaptcha(@QueryMap options: Map<String, String>): String
}