package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid
import org.jetbrains.kotlin.psi.KtValueArgumentList

class KtTreeStatsCollector(statsStorage: StatsStorage) : KtTreeVisitorVoid() {

    private val ktElementStatsCollector = KtElementStatsCollector(statsStorage)
    private val ktValueArgumentListStatsCollector = KtValueArgumentListStatsCollector(statsStorage)

    override fun visitKtElement(element: KtElement) {
        element.accept(ktElementStatsCollector)
        super.visitKtElement(element)
    }

    override fun visitValueArgumentList(list: KtValueArgumentList) {
        list.accept(ktValueArgumentListStatsCollector)
    }
}
