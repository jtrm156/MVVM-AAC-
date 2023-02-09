package com.example.practicemvvm

import com.google.gson.annotations.SerializedName

data class SolveAcGetUserDataModel (
    @SerializedName("handle")
    val handle : String,

    @SerializedName("bio")
    val bio : String,

    @SerializedName("organizations")
    val organization : List<OrganizationModel>,

    @SerializedName("background")
    val background : BackGroundData,

    @SerializedName("profileImageUrl")
    val profileImageUrl : String,

    @SerializedName("solvedCount")
    val solvedCount : Int,

    @SerializedName("tier")
    val tier : Int
) {
    var code : Int = 0
    var tierText : String = ""

    data class OrganizationModel(
        @SerializedName("name")
        val name : String
    )

    data class BackGroundData(
        @SerializedName("backgroundImageUrl")
        val backgroundImageUrl : String,

        @SerializedName("displayName")
        val displayName : String,

        @SerializedName("displayDescription")
        val displayDescription : String
    )
}