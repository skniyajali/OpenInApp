package com.niyaj.openinappold.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niyaj.openinappold.features.campaigns.presentation.CampaignsScreen
import com.niyaj.openinappold.features.common.ui.theme.OpenInAppOldTheme
import com.niyaj.openinappold.features.common.utils.Screens.CAMPAIGNS_SCREEN_ROUTE
import com.niyaj.openinappold.features.common.utils.Screens.COURSES_SCREEN_ROUTE
import com.niyaj.openinappold.features.common.utils.Screens.DASHBOARD_SCREEN_ROUTE
import com.niyaj.openinappold.features.common.utils.Screens.PROFILE_SCREEN_ROUTE
import com.niyaj.openinappold.features.courses.presentation.CoursesScreen
import com.niyaj.openinappold.features.dashboard.presentation.DashboardScreen
import com.niyaj.openinappold.features.profile.presentation.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            OpenInAppOldTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController ,
                        startDestination = DASHBOARD_SCREEN_ROUTE
                    ) {
                        composable(DASHBOARD_SCREEN_ROUTE) {
                            DashboardScreen(navController = navController)
                        }
                        
                        composable(COURSES_SCREEN_ROUTE) {
                            CoursesScreen(navController = navController)
                        }
                        
                        composable(CAMPAIGNS_SCREEN_ROUTE) {
                            CampaignsScreen(navController = navController)
                        }
                        
                        composable(PROFILE_SCREEN_ROUTE) {
                            ProfileScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}