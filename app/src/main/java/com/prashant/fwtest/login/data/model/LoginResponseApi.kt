package com.prashant.fwtest.article.api

import com.google.gson.annotations.SerializedName

data class LoginResponseApi(@SerializedName("access_token") val accessToken: String)