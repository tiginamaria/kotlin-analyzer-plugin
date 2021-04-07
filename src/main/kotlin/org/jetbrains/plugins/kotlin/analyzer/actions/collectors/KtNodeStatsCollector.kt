package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtVisitorVoid

abstract class KtNodeStatsCollector(private val statsStorage: StatsStorage) : KtVisitorVoid() {

    fun updateStatistics(tag: String, value: Int) {
        statsStorage.inc(tag, value)
    }

}
