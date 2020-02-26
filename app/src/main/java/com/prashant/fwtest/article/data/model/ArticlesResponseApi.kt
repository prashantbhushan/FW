package com.prashant.fwtest.article.api

import com.google.gson.annotations.SerializedName


data class ArticlesListResponseApi(val article: List<ArticlesResponseItem>)

data class ArticlesResponseItem(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("date") val date: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("thumbnail_template_url") val thumbnailTemplateUrl: String
)
