package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error_code"     ) var errorCode     : Int?                     = null,
    @SerializedName("error_msg"      ) var errorMsg      : String?                  = null,
    @SerializedName("captcha_sid"    ) var captchaSid    : String?                  = null,
    @SerializedName("captcha_img"    ) var captchaImg    : String?                  = null,
    @SerializedName("request_params" ) var requestParams : ArrayList<RequestParams> = arrayListOf()
) {
    data class RequestParams (
        @SerializedName("key"   ) var key   : String? = null,
        @SerializedName("value" ) var value : String? = null

    )
}
