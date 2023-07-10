package com.niyaj.openinappold.features.courses.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.niyaj.openinappold.features.common.components.StandardScaffold

@Composable
fun CoursesScreen(
    navController: NavController,
) {
    StandardScaffold(
        navController = navController,
        title = "Courses",
    ) {

    }
}