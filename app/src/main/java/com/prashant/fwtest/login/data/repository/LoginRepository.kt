package com.prashant.fwtest.login.data.repository

import com.prashant.fwtest.article.api.LoginRequestApi
import com.prashant.fwtest.login.data.repository.local.LoginPreferencesHelper
import io.reactivex.Single
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: LoginDataSource,
    private val localDataSource: LoginPreferencesHelper
) {

    fun login(userId: String, passWord: String): Single<Boolean> {
        return remoteDataSource.login(LoginRequestApi(userId, passWord)).flatMap {
            localDataSource.setAccessToken(it.accessToken)
            Single.just(true)
        }
    }

    fun logout() =
        Single.just(localDataSource.clearAccessToken())

    fun isUserLoggedIn(): Single<Boolean> =
        Single.just(localDataSource.isUserLoggedIn())
}