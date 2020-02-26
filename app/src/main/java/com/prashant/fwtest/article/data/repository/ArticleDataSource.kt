package com.prashant.fwtest.article.data.repository

import com.prashant.fwtest.article.api.ArticleResponseApi
import com.prashant.fwtest.article.api.ArticlesResponseItem
import io.reactivex.Single

interface ArticleDataSource {

    fun loadArticlesList(): Single<List<ArticlesResponseItem>>

    fun loadArticle(articleId: Int): Single<ArticleResponseApi>
}