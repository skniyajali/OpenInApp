package com.niyaj.openinappold.features.dashboard.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.niyaj.openinappold.R
import com.niyaj.openinappold.features.common.ui.theme.SpaceLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.common.ui.theme.SpaceMediumMax
import com.niyaj.openinappold.features.common.ui.theme.SpaceSmall
import com.niyaj.openinappold.features.common.ui.theme.SpaceSmallMax
import com.niyaj.openinappold.features.common.ui.theme.containerColor
import com.niyaj.openinappold.features.common.ui.theme.outlineColor
import com.niyaj.openinappold.features.common.ui.theme.primaryColor
import com.niyaj.openinappold.features.common.ui.theme.secondaryTextColor
import com.niyaj.openinappold.features.dashboard.domain.model.Entry
import com.niyaj.openinappold.features.dashboard.domain.utils.DashboardScreenTags.OVERVIEW
import com.niyaj.openinappold.features.common.utils.Extensions
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
import java.math.RoundingMode

@Composable
fun Overview(
    modifier: Modifier = Modifier,
    data: Map<String, Int>,
    title: String = OVERVIEW
) {
    Card(
        modifier = modifier
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
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 1.43.em,
                    color = secondaryTextColor,
                )

                DateSelectionBox(
                    startDate = "20 sep",
                    endDate = "28 oct",
                    onClickDate = {}
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ChartBar(overallUrlChart = data)
        }
    }
}


@Composable
fun DateSelectionBox(
    modifier: Modifier = Modifier,
    startDate: String,
    endDate: String,
    @DrawableRes
    iconRes: Int = R.drawable.time,
    onClickDate: () -> Unit = {},
) {
    if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, outlineColor),
                    RoundedCornerShape(SpaceSmallMax)
                )
                .clip(RoundedCornerShape(SpaceSmallMax))
                .clickable {
                    onClickDate()
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
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black,
            )

            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "Clock Icon",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@Composable
fun ChartBar(
    overallUrlChart: Map<String, Int>
) {

    val data = overallUrlChart.entries.groupBy {
        Extensions.getFormattedMonth(it.key)
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