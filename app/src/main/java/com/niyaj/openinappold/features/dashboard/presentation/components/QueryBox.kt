package com.niyaj.openinappold.features.dashboard.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.niyaj.openinappold.R
import com.niyaj.openinappold.features.common.ui.theme.SpaceLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.common.ui.theme.ctaColor
import com.niyaj.openinappold.features.common.ui.theme.lightPrimary
import com.niyaj.openinappold.features.common.ui.theme.primaryColor
import com.niyaj.openinappold.features.dashboard.domain.utils.DashboardScreenTags.FREQ_ASKED_Q_BTN
import com.niyaj.openinappold.features.dashboard.domain.utils.DashboardScreenTags.TALK_WITH_US_BTN

@Composable
fun CtaBoxes(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(SpaceLarge)
    ) {
        CtaBox(
            title = TALK_WITH_US_BTN,
            iconRes = R.drawable.whatsapp,
            backgroundColor = ctaColor.copy(0.12f),
            borderColor = ctaColor,
        )

        CtaBox(
            title = FREQ_ASKED_Q_BTN,
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