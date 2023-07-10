package com.niyaj.openinappold.features.dashboard.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import com.niyaj.openinappold.features.common.components.StandardOutlineButton
import com.niyaj.openinappold.features.common.components.StandardOutlineIconButton
import com.niyaj.openinappold.features.common.ui.theme.SpaceLarge
import com.niyaj.openinappold.features.common.ui.theme.SpaceLargeMini
import com.niyaj.openinappold.features.common.ui.theme.SpaceMedium
import com.niyaj.openinappold.features.common.ui.theme.SpaceSmallMax
import com.niyaj.openinappold.features.common.ui.theme.containerColor
import com.niyaj.openinappold.features.common.ui.theme.lightPrimary
import com.niyaj.openinappold.features.common.ui.theme.outlineColor
import com.niyaj.openinappold.features.common.ui.theme.primaryColor
import com.niyaj.openinappold.features.common.ui.theme.secondaryTextColor
import com.niyaj.openinappold.features.common.utils.Extensions
import com.niyaj.openinappold.features.dashboard.domain.model.Data
import com.niyaj.openinappold.features.dashboard.domain.model.Links
import kotlinx.coroutines.launch

@Composable
fun Tabs(
    data: Data
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Top Links", "Recent Links")

    val interactionSource = remember {
        MutableInteractionSource()
    }

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
                            .weight(1f)
                            .background(primaryColor, RoundedCornerShape(50))
                            .padding(horizontal = SpaceLarge, vertical = SpaceSmallMax)
                            .clip(RoundedCornerShape(50))
                            .indication(interactionSource, null)
                        else Modifier
                            .weight(1f)
                            .indication(interactionSource, null),
                    ) {
                        Text(
                            text = tab,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (tabIndex == index) containerColor else secondaryTextColor
                        )
                    }
                }
            }

            StandardOutlineIconButton(icon = Icons.Outlined.Search, contentDescription = "Search icon")
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

        StandardOutlineButton(
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
                            text = Extensions.formattedDate(link.createdAt),
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