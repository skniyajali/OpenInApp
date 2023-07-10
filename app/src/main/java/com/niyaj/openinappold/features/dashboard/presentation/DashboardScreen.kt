package com.niyaj.openinappold.features.dashboard.presentation

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.niyaj.openinappold.R
import com.niyaj.openinappold.features.common.components.ErrorMessage
import com.niyaj.openinappold.features.common.components.LoadingIndicator
import com.niyaj.openinappold.features.common.components.StandardScaffold
import com.niyaj.openinappold.features.common.ui.theme.SpaceExtraLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceLargeMax
import com.niyaj.openinappold.features.common.ui.theme.SpaceLargeMini
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.common.ui.theme.SpaceMediumMax
import com.niyaj.openinappold.features.common.ui.theme.SpaceSmall
import com.niyaj.openinappold.features.common.ui.theme.SpaceSmallMax
import com.niyaj.openinappold.features.common.ui.theme.containerColor
import com.niyaj.openinappold.features.common.ui.theme.ctaColor
import com.niyaj.openinappold.features.common.ui.theme.lightPrimary
import com.niyaj.openinappold.features.common.ui.theme.outlineColor
import com.niyaj.openinappold.features.common.ui.theme.primaryColor
import com.niyaj.openinappold.features.common.ui.theme.secondaryTextColor
import com.niyaj.openinappold.features.dashboard.domain.model.Data
import com.niyaj.openinappold.features.dashboard.domain.model.Entry
import com.niyaj.openinappold.features.dashboard.domain.model.Links
import com.niyaj.openinappold.features.utils.Extensions.formattedDate
import com.niyaj.openinappold.features.utils.Extensions.getFirstDate
import com.niyaj.openinappold.features.utils.Extensions.getFormattedMonth
import com.niyaj.openinappold.features.utils.Extensions.getGreetingHour
import com.niyaj.openinappold.features.utils.Extensions.getLastDate
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.axis.formatter.DecimalFormatAxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import kotlinx.coroutines.launch
import java.math.RoundingMode

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle().value

    StandardScaffold(
        navController = navController,
        title = "Dashboard",
        navActionsIcon = R.drawable.settings,
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

                        item("Chart") {
                            ChartBox(dashboard.data.overallUrlChart)
                        }

                        item("TotalCounts") {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(
                                    SpaceLargeMax,
                                    Alignment.Start
                                )
                            ) {
                                item {
                                    IconBox(
                                        title = dashboard.todayClicks.toString(),
                                        secondaryText = "Today's Click",
                                        iconRes = R.drawable.software
                                    )
                                }
                                item {
                                    IconBox(
                                        title = dashboard.topLocation,
                                        secondaryText = "Top Location",
                                        iconRes = R.drawable.pin
                                    )
                                }
                                item {
                                    IconBox(
                                        title = dashboard.topSource,
                                        secondaryText = "Top Source",
                                        iconRes = R.drawable.generic
                                    )
                                }
                                item {
                                    IconBox(
                                        title = dashboard.startTime,
                                        secondaryText = "Best Time",
                                        iconRes = R.drawable.software
                                    )
                                }
                            }
                        }

                        item("ViewAnalytics") {
                            OutlineButton(
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


@Composable
fun Greeting(
    username: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
    ) {
        Text(
            text = getGreetingHour(),
            color = secondaryTextColor,
            style = MaterialTheme.typography.bodyLarge,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = username,
                color = Color.Black,
                lineHeight = 1.33.em,
                style = MaterialTheme.typography.headlineSmall,
            )

            Image(
                painter = painterResource(id = R.drawable.hi),
                contentDescription = "hi..",
                modifier = Modifier
                    .size(size = 28.dp)
            )
        }
    }
}


@Composable
private fun ChartBox(
    overallUrlChart: Map<String, Int>
) {
    val startDate = overallUrlChart.getFirstDate()
    val endDate = overallUrlChart.getLastDate()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(SpaceMedium),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpaceMediumMax, vertical = SpaceLarge),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Overview",
                    color = secondaryTextColor,
                    lineHeight = 1.43.em,
                    style = MaterialTheme.typography.bodySmall
                )

                DateSelectionBox(startDate, endDate)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ChartBar(overallUrlChart = overallUrlChart)
        }
    }
}


@Composable
fun ChartBar(
    overallUrlChart: Map<String, Int>
) {

    val data = overallUrlChart.entries.groupBy {
        getFormattedMonth(it.key)
    }
    val result: List<Pair<String, Int>> = data.map { entry ->
        val total = entry.value.sumOf { it.value }

        Pair(entry.key, total)
    }

    val model = result.mapIndexed { index, pair ->
        Entry(pair.first, index.toFloat(), pair.second.toFloat())
    }.let {
        ChartEntryModelProducer(it)
    }

    val bottomValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, chartValues ->
            (chartValues.chartEntryModel.entries.first().getOrNull(value.toInt()) as? Entry)
                ?.run { month }
                .orEmpty()
        }

    Chart(
        chart = lineChart(
            lines = listOf(
                lineSpec(
                    lineColor = primaryColor,
                    lineBackgroundShader = verticalGradient(
                        arrayOf(primaryColor.copy(0.5f), primaryColor.copy(alpha = 0f)),
                    ),
                ),
            ),
        ),
        model = model.getModel(),
        startAxis = startAxis(
            valueFormatter = DecimalFormatAxisValueFormatter(
                "###",
                RoundingMode.CEILING
            )
        ),
        bottomAxis = bottomAxis(
            valueFormatter = bottomValueFormatter
        ),
    )
}

@Composable
fun DateSelectionBox(
    startDate: String,
    endDate: String,
    onClick: () -> Unit = {},
) {
    if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    border = BorderStroke(1.dp, outlineColor),
                    shape = RoundedCornerShape(SpaceSmallMax)
                )
                .clip(RoundedCornerShape(SpaceSmallMax))
                .clickable {
                    onClick()
                }
                .padding(
                    start = SpaceMedium,
                    end = SpaceSmall,
                    top = SpaceSmallMax,
                    bottom = SpaceSmallMax
                )
        ) {
            Text(
                text = "$startDate - $endDate",
                color = Color.Black,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
            )
            Image(
                painter = painterResource(id = R.drawable.time),
                contentDescription = "Time",
                modifier = Modifier
                    .size(size = 16.dp)
            )
        }
    }
}

@Composable
fun IconBox(
    title: String,
    secondaryText: String,
    @DrawableRes
    iconRes: Int
) {
    Box(
        modifier = Modifier
            .size(size = 120.dp)
            .clip(shape = RoundedCornerShape(SpaceMedium))
            .background(containerColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .offset(
                    x = SpaceMediumMax,
                    y = SpaceMediumMax
                )
        ) {
            CircleIcon(iconRes = iconRes, contentDescription = title)

            Spacer(modifier = Modifier.height(SpaceLarge))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    lineHeight = 1.5.em,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = secondaryText,
                    color = secondaryTextColor,
                    lineHeight = 1.43.em,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

@Composable
fun CircleIcon(
    @DrawableRes
    iconRes: Int,
    contentDescription: String,
) {
    Box(
        modifier = Modifier
            .size(size = 32.dp)
            .clip(shape = CircleShape)
            .background(color = primaryColor.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier
                .align(Alignment.Center)
                .size(size = 20.dp)
        )
    }
}


@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, outlineColor),
                shape = RoundedCornerShape(SpaceMedium)
            )
            .clip(RoundedCornerShape(SpaceMedium))
            .clickable {
                onClick()
            }
            .padding(SpaceMediumMax)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = iconModifier,
        )

        Text(
            text = title,
            color = Color.Black,
            lineHeight = 1.5.em,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
fun OutlineIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit = {},
) {
    OutlinedIconButton(
        modifier = modifier,
        shape = RoundedCornerShape(SpaceMedium),
        border = BorderStroke(1.dp, outlineColor),
        onClick = onClick,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = outlineColor,
        )
    }
}


@Composable
fun Tabs(
    data: Data
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Top Links", "Recent Links")

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.weight(2f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                        },
                        selectedContentColor = containerColor,
                        unselectedContentColor = outlineColor,
                        modifier = if (tabIndex == index) Modifier
                            .clip(RoundedCornerShape(50))
                            .background(primaryColor, RoundedCornerShape(50))
                            .padding(horizontal = SpaceLarge, vertical = SpaceSmallMax)
                            .weight(1f)
                        else Modifier.weight(1f)
                    ) {
                        Text(
                            text = tab,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (tabIndex == index) containerColor else outlineColor
                        )
                    }
                }
            }

            OutlineIconButton(icon = Icons.Outlined.Search, contentDescription = "Search icon")
        }


        Spacer(modifier = Modifier.height(28.dp))

        when (tabIndex) {
            0 -> {
                TabContents(data.topLinks)
            }

            1 -> {
                TabContents(data.links)
            }
        }
    }
}

@Composable
fun TabContents(
    links: List<Links>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SpaceLargeMini)
    ) {
        links.forEach {
            key(it.app.plus(it.urlId)) {
                TabContent(it)
            }
        }

        OutlineButton(
            title = "View All Links",
            icon = Icons.Default.Link,
            iconModifier = Modifier.rotate(-45f)
        )
    }
}


@Composable
fun TabContent(
    link: Links
) {
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope()

    val interactionSource = remember {
        MutableInteractionSource()
    }

    fun copyToClipboard(text: String) {
        scope.launch {
            clipboardManager.setText(AnnotatedString(text))
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(SpaceMedium),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier
                        .width(180.dp)
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        model = link.originalImage,
                        contentDescription = link.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(48.dp)
                            .border(BorderStroke(1.dp, outlineColor), RoundedCornerShape(8.dp))
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Column(
                        modifier = Modifier
                            .width(120.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(
                            2.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = link.title,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Text(
                            text = formattedDate(link.createdAt),
                            style = MaterialTheme.typography.labelSmall,
                            color = secondaryTextColor,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = link.totalClicks.toString(),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black
                    )

                    Text(
                        text = "Clicks",
                        style = MaterialTheme.typography.labelSmall,
                        color = secondaryTextColor,
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(lightPrimary)
                .border(
                    border = BorderStroke(1.dp, primaryColor),
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .clip(shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .clickable(
                    indication = null,
                    interactionSource = interactionSource,
                ) {
                    copyToClipboard(link.webLink)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(width = 213.dp)
                ) {
                    Text(
                        text = link.webLink,
                        color = primaryColor,
                        lineHeight = 1.14.em,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Files",
                    tint = primaryColor
                )
            }
        }
    }
}

@Composable
fun CtaBoxes() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(SpaceLarge)
    ) {
        CtaBox(
            title = "Talk with us",
            iconRes = R.drawable.whatsapp,
            backgroundColor = ctaColor.copy(0.12f),
            borderColor = ctaColor,
        )

        CtaBox(
            title = "Frequently Asked Questions",
            iconRes = R.drawable.question,
        )
    }
}

@Composable
fun CtaBox(
    title: String,
    @DrawableRes
    iconRes: Int,
    backgroundColor: Color = lightPrimary,
    borderColor: Color = primaryColor,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(SpaceMedium))
            .border(
                BorderStroke(1.dp, borderColor.copy(alpha = 0.32f)),
                RoundedCornerShape(SpaceMedium)
            )
            .clip(RoundedCornerShape(SpaceMedium))
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = borderColor
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}