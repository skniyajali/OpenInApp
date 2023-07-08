package com.niyaj.openinappold.features.dashboard.domain.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("recent_links")
    val links: List<Links> = emptyList(),

    @SerializedName("top_links")
    val topLinks: List<Links> = emptyList(),

    @SerializedName("overall_url_chart")
    val overallUrlChart: Map<String, Int> = emptyMap()
)

