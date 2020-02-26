package com.prashant.fwtest.login.data.api

import com.prashant.fwtest.article.api.LoginRequestApi
import com.prashant.fwtest.article.api.LoginResponseApi
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("auth/token")
    fun login(@Body body: LoginRequestApi): Single<LoginResponseApi>
}