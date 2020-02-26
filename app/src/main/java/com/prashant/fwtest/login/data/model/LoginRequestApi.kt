package com.prashant.fwtest.article.api


import com.google.gson.annotations.SerializedName

data class LoginRequestApi(
    @SerializedName("username") val userName: String,
    @SerializedName("password") val passWord: String,
    @SerializedName("grant_type") val grantType: String = "password"
)
