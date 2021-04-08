package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtVisitorVoid


/** This class collects statistics about [KtElement]. */
class KtElementStatsCollector(private val statsStorage: StatsStorage) : KtVisitorVoid() {

    /** Increase occurrence of received [KtElement] */
    override fun visitKtElement(element: KtElement) {
        statsStorage.inc(element.toString(), 1)
    }
}
