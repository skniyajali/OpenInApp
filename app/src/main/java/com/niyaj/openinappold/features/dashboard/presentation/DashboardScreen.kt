package com.niyaj.openinappold.features.dashboard.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.niyaj.openinappold.R
import com.niyaj.openinappold.features.common.components.ErrorMessage
import com.niyaj.openinappold.features.common.components.LoadingIndicator
import com.niyaj.openinappold.features.common.components.StandardOutlineButton
import com.niyaj.openinappold.features.common.components.StandardScaffold
import com.niyaj.openinappold.features.common.ui.theme.SpaceExtraLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceLargeMax
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.dashboard.domain.utils.DashboardScreenTags.TITLE
import com.niyaj.openinappold.features.dashboard.presentation.components.CtaBoxes
import com.niyaj.openinappold.features.dashboard.presentation.components.Greeting
import com.niyaj.openinappold.features.dashboard.presentation.components.Overview
import com.niyaj.openinappold.features.dashboard.presentation.components.Tabs
import com.niyaj.openinappold.features.dashboard.presentation.components.TotalCounts

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle().value

    StandardScaffold(
        navController = navController,
        title = TITLE,
        navActions = {
            FilledTonalIconButton(
                onClick = {},
                shape = RoundedCornerShape(SpaceMedium),
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = Color.White.copy(alpha = 0.12f)
                ),
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "icon",
                    tint = Color.White
                )
            }
        }
    ) {
        Crossfade(
            targetState = uiState,
            label = "Dashboard State"
        ) { state ->
            when (state) {
                is DashboardState.Loading -> LoadingIndicator()
                is DashboardState.Error -> ErrorMessage(
                    error = state.error ?: "Unable to load dashboard data"
                )

                is DashboardState.Success -> {
                    val dashboard = state.data

                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = SpaceExtraLarge,
                                bottom = 0.dp,
                                start = SpaceLarge,
                                end = SpaceLarge
                            ),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(SpaceLargeMax)
                    ) {
                        item("Greeting") {
                            Greeting(username = "Sk Niyaj Ali")
                        }

                        item("Overview") {
                            Overview(data = dashboard.data.overallUrlChart)
                        }

                        item("TotalCounts") {
                            TotalCounts(dashboard = dashboard)
                        }

                        item("ViewAnalytics") {
                            StandardOutlineButton(
                                title = "View Analytics",
                                icon = Icons.Default.TrendingUp,
                                onClick = {}
                            )
                        }

                        item("Tabs") {
                            Tabs(data = dashboard.data)
                        }

                        item("ctaBoxes") {
                            Spacer(modifier = Modifier.height(SpaceLargeMax))

                            CtaBoxes()

                            Spacer(modifier = Modifier.height(SpaceLargeMax))
                        }
                    }
                }
            }
        }
    }
}