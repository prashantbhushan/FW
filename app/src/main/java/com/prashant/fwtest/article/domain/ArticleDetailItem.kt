package com.prashant.fwtest.article.domain

import com.prashant.fwtest.article.api.ArticleResponseApi

data class ArticleDetailItem(
    val id: Int,
    val title: String,
    val summary: String,
    val content: String,
    val date: String,
    val thumbnailUrl: String,
    val thumbnailTemplateUrl: String,
    val imageUrl: String,
    val sourceUrl: String
)

fun ArticleResponseApi.toDomain() = ArticleDetailItem(
    id,
    title,
    summary,
    content,
    date,
    thumbnailUrl,
    thumbnailTemplateUrl,
    imageUrl,
    sourceUrl
)