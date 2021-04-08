package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtValueArgumentList
import org.jetbrains.kotlin.psi.KtVisitorVoid

/** This class collects statistics about [KtValueArgumentList]. */
class KtValueArgumentListStatsCollector(private val statsStorage: StatsStorage) : KtVisitorVoid() {

    companion object {
        private const val ARGUMENTS_COUNT_TAG = "ARGUMENTS_COUNT"
    }

    /** Increase total value argument lists length on given [KtValueArgumentList] length */
    override fun visitValueArgumentList(list: KtValueArgumentList) {
        statsStorage.inc(ARGUMENTS_COUNT_TAG, list.arguments.size)
    }
}
