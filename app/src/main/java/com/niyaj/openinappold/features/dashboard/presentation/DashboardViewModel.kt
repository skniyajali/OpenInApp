package com.niyaj.openinappold.features.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niyaj.openinappold.features.dashboard.domain.repository.DashboardRepository
import com.niyaj.openinappold.features.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DashboardState>(DashboardState.Loading)
    val state = _state.asStateFlow()

    init {
        getDashboardData()
    }

    private fun getDashboardData() {
        viewModelScope.launch {
            dashboardRepository.getDashboardData().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = DashboardState.Error(result.message)
                    }

                    is Resource.Success -> {
                        result.data?.let { data ->
                            _state.value = DashboardState.Success(data)
                        }
                    }
                }
            }
        }
    }
}