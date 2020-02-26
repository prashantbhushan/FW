package com.prashant.fwtest.article.api

import com.google.gson.annotations.SerializedName

data class ArticleResponseApi(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("content") val content: String,
    @SerializedName("date") val date: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("thumbnail_template_url") val thumbnailTemplateUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("source_url") val sourceUrl: String
)