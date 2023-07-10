package com.niyaj.openinappold.features.campaigns.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.niyaj.openinappold.features.common.components.StandardScaffold

@Composable
fun CampaignsScreen(
    navController: NavController
) {
    StandardScaffold(
        navController = navController,
        title = "Campaigns",
    ) {

    }
}