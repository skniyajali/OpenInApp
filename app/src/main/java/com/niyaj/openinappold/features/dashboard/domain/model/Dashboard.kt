package com.niyaj.openinappold.features.dashboard.domain.model

import com.google.gson.annotations.SerializedName

data class Dashboard(
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("statusCode")
    val statusCode: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("support_whatsapp_number")
    val supportWhatsappNumber: String,

    @SerializedName("extra_income")
    val extraIncome: Double,

    @SerializedName("total_links")
    val totalLinks: Int,

    @SerializedName("total_clicks")
    val totalClicks: Int,

    @SerializedName("today_clicks")
    val todayClicks: Int,

    @SerializedName("top_source")
    val topSource: String,

    @SerializedName("top_location")
    val topLocation: String,

    @SerializedName("startTime")
    val startTime: String,

    @SerializedName("links_created_today")
    val linksCreatedToday: Int,

    @SerializedName("applied_campaign")
    val appliedCampaign: Int,

    @SerializedName("data")
    val data: Data = Data()
)
