package com.niyaj.openinappold.features.dashboard.di

import com.niyaj.openinappold.features.dashboard.data.repository.DashboardRepositoryImpl
import com.niyaj.openinappold.features.dashboard.data.service.DashboardService
import com.niyaj.openinappold.features.dashboard.domain.repository.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {

    @Singleton
    @Provides
    fun provideDashboardRepository(dashboardService: DashboardService): DashboardRepository {
        return DashboardRepositoryImpl(dashboardService)
    }
}