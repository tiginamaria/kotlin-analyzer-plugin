package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtValueArgumentList

class KtValueArgumentListStatsCollector(statsStorage: StatsStorage) : KtNodeStatsCollector(statsStorage) {

    companion object {
        private const val ARGUMENTS_COUNT_TAG = "ARGUMENTS_COUNT"
    }

    override fun visitValueArgumentList(list: KtValueArgumentList) {
        updateStatistics(ARGUMENTS_COUNT_TAG, list.arguments.size)
    }
}
