package com.prashant.fwtest.login.data.repository

import com.prashant.fwtest.article.api.LoginRequestApi
import com.prashant.fwtest.article.api.LoginResponseApi
import io.reactivex.Single


interface LoginDataSource {

    fun login(request: LoginRequestApi): Single<LoginResponseApi>

    fun logout() {}
}