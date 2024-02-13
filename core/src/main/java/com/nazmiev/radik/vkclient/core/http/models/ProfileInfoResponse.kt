package com.nazmiev.radik.vkclient.core.http.models

import com.google.gson.annotations.SerializedName


data class ProfileInfoResponse (

  @SerializedName("response" ) var response : Response?,
  @SerializedName("error") val error: Error?

) {
  data class Response (

    @SerializedName("id"                  ) var id                 : Int?                        = null,
    @SerializedName("home_town"           ) var homeTown           : String?                     = null,
    @SerializedName("status"              ) var status             : String?                     = null,
    @SerializedName("photo_200"           ) var photo200           : String?                     = null,
    @SerializedName("is_service_account"  ) var isServiceAccount   : Boolean?                    = null,
    @SerializedName("bdate"               ) var bdate              : String?                     = null,
    @SerializedName("verification_status" ) var verificationStatus : String?                     = null,
    @SerializedName("promo_verifications" ) var promoVerifications : ArrayList<String>           = arrayListOf(),
    @SerializedName("first_name"          ) var firstName          : String?                     = null,
    @SerializedName("last_name"           ) var lastName           : String?                     = null,
    @SerializedName("bdate_visibility"    ) var bdateVisibility    : Int?                        = null,
    @SerializedName("city"                ) var city               : City?                       = City(),
    @SerializedName("country"             ) var country            : Country?                    = Country(),
    @SerializedName("phone"               ) var phone              : String?                     = null,
    @SerializedName("relation"            ) var relation           : Int?                        = null,
    @SerializedName("relation_partner"    ) var relationPartner    : RelationPartner?            = RelationPartner(),
    @SerializedName("relation_requests"   ) var relationRequests   : ArrayList<RelationRequests> = arrayListOf(),
    @SerializedName("screen_name"         ) var screenName         : String?                     = null,
    @SerializedName("sex"                 ) var sex                : Int?                        = null

  ) {
    data class City (

      @SerializedName("id"    ) var id    : Int?    = null,
      @SerializedName("title" ) var title : String? = null

    )

    data class Country (

      @SerializedName("id"    ) var id    : Int?    = null,
      @SerializedName("title" ) var title : String? = null

    )

    data class RelationPartner (

      @SerializedName("id"                ) var id              : Int?     = null,
      @SerializedName("first_name"        ) var firstName       : String?  = null,
      @SerializedName("last_name"         ) var lastName        : String?  = null,
      @SerializedName("can_access_closed" ) var canAccessClosed : Boolean? = null,
      @SerializedName("is_closed"         ) var isClosed        : Boolean? = null

    )

    data class RelationRequests (

      @SerializedName("id"                ) var id              : Int?     = null,
      @SerializedName("first_name"        ) var firstName       : String?  = null,
      @SerializedName("last_name"         ) var lastName        : String?  = null,
      @SerializedName("can_access_closed" ) var canAccessClosed : Boolean? = null,
      @SerializedName("is_closed"         ) var isClosed        : Boolean? = null

    )
  }
}