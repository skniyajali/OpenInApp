package com.niyaj.openinappold.features.dashboard.data.service

import com.niyaj.openinappold.features.dashboard.domain.model.Dashboard
import retrofit2.http.GET
import retrofit2.http.Query

interface DashboardService {

    @GET("dashboardNew/")
    suspend fun getData(): Dashboard
}