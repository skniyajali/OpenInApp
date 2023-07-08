package com.niyaj.openinappold.features.dashboard.domain.model

import com.patrykandpatrick.vico.core.entry.ChartEntry

class Entry(
    val month: String,
    override val x: Float,
    override val y: Float,
) : ChartEntry {
    override fun withY(y: Float) = Entry(month, x, y)
}