package com.prashant.fwtest.login.data.repository.remote

import com.prashant.fwtest.article.api.LoginRequestApi
import com.prashant.fwtest.article.api.LoginResponseApi
import com.prashant.fwtest.login.data.api.LoginApiService
import com.prashant.fwtest.login.data.repository.LoginDataSource
import io.reactivex.Single
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(val apiService: LoginApiService) :
    LoginDataSource {

    override fun login(request: LoginRequestApi): Single<LoginResponseApi> {
        return apiService.login(request)
    }
}