package com.niyaj.openinappold.features.dashboard.data.repository

import com.niyaj.openinappold.features.dashboard.data.service.DashboardService
import com.niyaj.openinappold.features.dashboard.domain.model.Dashboard
import com.niyaj.openinappold.features.dashboard.domain.repository.DashboardRepository
import com.niyaj.openinappold.features.common.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val dashboardService: DashboardService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : DashboardRepository {

    override suspend fun getDashboardData(): Flow<Resource<Dashboard>> {
        return channelFlow {
            try {
                withContext(ioDispatcher) {
                    val data = dashboardService.getData()

                    send(Resource.Success(data))
                }
            }catch (e: Exception) {
                send(Resource.Error(e.message))
            }
        }
    }
}