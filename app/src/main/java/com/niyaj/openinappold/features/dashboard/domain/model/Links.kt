package com.niyaj.openinappold.features.dashboard.domain.model

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("url_id")
    val urlId: Int,

    @SerializedName("web_link")
    val webLink: String,

    @SerializedName("smart_link")
    val smartLink: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("total_clicks")
    val totalClicks: Int,

    @SerializedName("original_image")
    val originalImage: String,

    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("times_ago")
    val timesAgo: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("domain_id")
    val domainId: String,

    @SerializedName("url_prefix")
    val urlPrefix: String? = null,

    @SerializedName("url_suffix")
    val urlSuffix: String,

    @SerializedName("app")
    val app: String
)

