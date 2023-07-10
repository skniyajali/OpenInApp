package com.niyaj.openinappold.features.profile.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.niyaj.openinappold.features.common.components.StandardScaffold

@Composable
fun ProfileScreen(
    navController: NavController
) {
    StandardScaffold(
        navController = navController,
        title = "Profile",
    ) {

    }
}