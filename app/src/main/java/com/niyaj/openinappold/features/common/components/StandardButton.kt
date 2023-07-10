package com.niyaj.openinappold.features.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.common.ui.theme.SpaceMediumMax
import com.niyaj.openinappold.features.common.ui.theme.outlineColor
import com.niyaj.openinappold.features.common.ui.theme.primaryColor
import com.niyaj.openinappold.features.common.ui.theme.secondaryTextColor


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
fun StandardOutlineButton(
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
fun StandardOutlineIconButton(
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
            tint = secondaryTextColor,
        )
    }
}