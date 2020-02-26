package com.prashant.fwtest.article.domain

import com.prashant.fwtest.article.api.ArticlesResponseItem
import java.util.*

data class ArticleListItem(
    val id: Int,
    val title: String,
    val summary: String,
    val date: String,
    val thumbnailUrl: String,
    val thumbnailTemplateUrl: String
)

fun ArticlesResponseItem.toDomain() =
    ArticleListItem(id, title, summary, date, thumbnailUrl, thumbnailTemplateUrl)
