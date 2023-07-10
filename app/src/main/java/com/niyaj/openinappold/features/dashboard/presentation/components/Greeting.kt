package com.niyaj.openinappold.features.dashboard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.niyaj.openinappold.R
import com.niyaj.openinappold.features.common.ui.theme.secondaryTextColor
import com.niyaj.openinappold.features.common.utils.Extensions.getGreetingHour

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    username: String,
    greetingText: String = getGreetingHour(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
    ) {
        Text(
            text = greetingText,
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