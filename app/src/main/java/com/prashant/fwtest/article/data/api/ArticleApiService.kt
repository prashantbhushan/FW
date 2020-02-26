package com.prashant.fwtest.article.data.api

import com.prashant.fwtest.article.api.ArticleResponseApi
import com.prashant.fwtest.article.api.ArticlesResponseItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ArticleApiService {

    @GET("api/v1/articles")
    fun getArticles(@Header("Authorization") authHeader: String): Single<List<ArticlesResponseItem>>

    @GET("api/v1/articles/{articleId}")
    fun getArticle(@Header("Authorization") authHeader: String, @Path("articleId") articleId: Int): Single<ArticleResponseApi>
}