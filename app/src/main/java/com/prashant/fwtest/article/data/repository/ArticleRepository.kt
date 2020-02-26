package com.prashant.fwtest.article.data.repository

import com.prashant.fwtest.article.api.ArticleResponseApi
import com.prashant.fwtest.article.api.ArticlesResponseItem
import io.reactivex.Single
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val remoteDataSource: ArticleDataSource
) {

    fun loadArticlesList(): Single<List<ArticlesResponseItem>> {
        return remoteDataSource.loadArticlesList()
    }

    fun loadArticle(articleId: Int): Single<ArticleResponseApi> {
        return remoteDataSource.loadArticle(articleId)
    }
}