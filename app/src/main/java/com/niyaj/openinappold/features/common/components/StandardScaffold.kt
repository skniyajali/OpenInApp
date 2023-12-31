package com.niyaj.openinappold.features.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.niyaj.openinappold.R
import com.niyaj.openinappold.features.common.ui.theme.SpaceLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.common.ui.theme.SpaceMini
import com.niyaj.openinappold.features.common.ui.theme.containerColor
import com.niyaj.openinappold.features.common.ui.theme.outlineColor
import com.niyaj.openinappold.features.common.ui.theme.primaryColor
import com.niyaj.openinappold.features.common.ui.theme.secondaryTextColor
import com.niyaj.openinappold.features.common.ui.theme.surfaceColor
import com.niyaj.openinappold.features.common.utils.Screens.CAMPAIGNS_SCREEN_ROUTE
import com.niyaj.openinappold.features.common.utils.Screens.COURSES_SCREEN_ROUTE
import com.niyaj.openinappold.features.common.utils.Screens.DASHBOARD_SCREEN_ROUTE
import com.niyaj.openinappold.features.common.utils.Screens.PROFILE_SCREEN_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.Center,
    containerColor: Color = primaryColor,
    navActions: @Composable (RowScope.() -> Unit) = {},
    bottomBar: @Composable () -> Unit = {
        BottomNavigation(navController = navController, onClickFab = {})
    },
    content: @Composable (PaddingValues) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                },
                actions = navActions,
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = SpaceMedium),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp,
            ),
            shape = RoundedCornerShape(SpaceLarge, SpaceLarge),
            colors = CardDefaults.elevatedCardColors(
                containerColor = surfaceColor
            )
        ) {
            content(it)
        }
    }
}


@Composable
fun BottomNavigation(
    navController: NavController,
    onClickFab: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavItem(
            title = "Links",
            route = DASHBOARD_SCREEN_ROUTE,
            iconRes = R.drawable.link
        ),
        BottomNavItem(
            title = "Courses",
            route = COURSES_SCREEN_ROUTE,
            iconRes = R.drawable.files
        ),
        BottomNavItem(
            title = "Courses",
            route = CAMPAIGNS_SCREEN_ROUTE,
            iconRes = R.drawable.media
        ),
        BottomNavItem(
            title = "Courses",
            route = PROFILE_SCREEN_ROUTE,
            iconRes = R.drawable.user
        )
    )

    BottomAppBar(
        containerColor = containerColor,
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationBar(
            containerColor = containerColor,
            contentColor = outlineColor,
            modifier = Modifier
                .fillMaxWidth(),
        ){
            items.take(2).forEach {
                NavigationBarItem(
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.iconRes),
                            contentDescription = it.title,
                        )
                    },
                    label = {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 11.sp
                            ),
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = it.selectedColor,
                        selectedTextColor = it.selectedColor,
                        unselectedIconColor = it.unselectedColor,
                        unselectedTextColor = it.unselectedColor,
                        indicatorColor = containerColor,
                    )
                )
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(top = SpaceMini)
                    .offset(
                        y = SpaceMedium
                    ),
                onClick = onClickFab,
                shape = CircleShape,
                containerColor = primaryColor,
                contentColor = containerColor
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Fab Button"
                )
            }

            items.takeLast(2).forEach {
                NavigationBarItem(
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.iconRes),
                            contentDescription = it.title,
                        )
                    },
                    label = {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 11.sp
                            ),
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = it.selectedColor,
                        selectedTextColor = it.selectedColor,
                        unselectedIconColor = it.unselectedColor,
                        unselectedTextColor = it.unselectedColor,
                        indicatorColor = containerColor,
                    )
                )
            }
        }
    }
}

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector? = null,
    @DrawableRes
    val iconRes: Int,
    val selectedColor: Color = Color.Black,
    val unselectedColor: Color = secondaryTextColor
)