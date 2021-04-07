package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtElement

class KtElementStatsCollector(statsStorage: StatsStorage) : KtNodeStatsCollector(statsStorage) {

    companion object {
        private const val UNKNOWN_ELEMENT_TAG = "UNKNOWN_ELEMENT"
    }

    override fun visitKtElement(element: KtElement) {
        updateStatistics(element.toString(), 1)
    }
}
