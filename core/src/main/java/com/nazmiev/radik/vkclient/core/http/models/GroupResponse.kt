package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName


data class GroupResponse (
  @SerializedName("response" ) var response : Response? = Response(),
  @SerializedName("error") val error: Error?
) {

  data class Response (
    @SerializedName("count" ) var count : Int?             = null,
    @SerializedName("items" ) var items : ArrayList<Items> = arrayListOf()
  ) {

    data class Items (
      @SerializedName("id"          ) var id         : Int?    = null,
      @SerializedName("name"        ) var name       : String? = null,
      @SerializedName("screen_name" ) var screenName : String? = null,
      @SerializedName("is_closed"   ) var isClosed   : Int?    = null,
      @SerializedName("type"        ) var type       : String? = null,
      @SerializedName("photo_50"    ) var photo50    : String? = null,
      @SerializedName("photo_100"   ) var photo100   : String? = null,
      @SerializedName("photo_200"   ) var photo200   : String? = null
    )
  }
}