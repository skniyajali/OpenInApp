package com.niyaj.openinappold.features.dashboard.domain.repository

import com.niyaj.openinappold.features.dashboard.domain.model.Dashboard
import com.niyaj.openinappold.features.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun getDashboardData(): Flow<Resource<Dashboard>>
}