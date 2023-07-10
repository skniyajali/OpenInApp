package com.niyaj.openinappold.features.dashboard.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.niyaj.openinappold.R
import com.niyaj.openinappold.features.common.components.CircleIcon
import com.niyaj.openinappold.features.common.ui.theme.SpaceLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceLargeMax
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.common.ui.theme.SpaceMediumMax
import com.niyaj.openinappold.features.common.ui.theme.containerColor
import com.niyaj.openinappold.features.common.ui.theme.secondaryTextColor
import com.niyaj.openinappold.features.dashboard.domain.model.Dashboard

@Composable
fun TotalCounts(
    dashboard: Dashboard
) {
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