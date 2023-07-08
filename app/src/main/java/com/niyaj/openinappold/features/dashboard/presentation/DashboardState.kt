package com.niyaj.openinappold.features.dashboard.presentation

import com.niyaj.openinappold.features.dashboard.domain.model.Dashboard

sealed interface DashboardState {

    object Loading : DashboardState

    data class Success(val data: Dashboard) : DashboardState

    data class Error(val error: String? = null) : DashboardState
}