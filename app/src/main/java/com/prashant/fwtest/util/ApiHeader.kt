package com.prashant.fwtest.util

import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class ApiHeader @Inject constructor(@SerializedName("access_token") val accessToken: String)