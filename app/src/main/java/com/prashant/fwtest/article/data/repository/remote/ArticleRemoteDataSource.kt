package com.prashant.fwtest.article.data.repository.remote

import com.prashant.fwtest.article.api.ArticleResponseApi
import com.prashant.fwtest.article.api.ArticlesResponseItem
import com.prashant.fwtest.article.data.api.ArticleApiService
import com.prashant.fwtest.article.data.repository.ArticleDataSource
import com.prashant.fwtest.login.data.repository.local.LoginPreferencesHelper
import io.reactivex.Single
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val loginPreferencesHelper: LoginPreferencesHelper,
    private val apiService: ArticleApiService
) : ArticleDataSource {

    override fun loadArticlesList(): Single<List<ArticlesResponseItem>> =
        apiService.getArticles(loginPreferencesHelper.getAccessToken())

    override fun loadArticle(articleId: Int): Single<ArticleResponseApi> =
        apiService.getArticle(loginPreferencesHelper.getAccessToken(), articleId)
}