package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtElement


/** This class stores statistics about each [KtElement] occurrence in file by [KtElement] tag. */
class StatsStorage {
    private val statistics = mutableMapOf<String, Int>()

    /** Present all statistic stored */
    fun getAll(): Map<String, Int> {
        return statistics
    }

    /** Get [KtElement] statistic by [tag] */
    fun get(tag: String): Int? {
        return statistics[tag]
    }

    /** Set [KtElement] statistic [value] by [tag] */
    fun set(tag: String, value: Int) {
        statistics[tag] = value
    }

    /**
     * Increase [KtElement] statistic by [tag] on value [value].
     * If there no statistic by [tag], initialize it with [initialValue].
     */
    fun inc(tag: String, value: Int, initialValue: Int = 0) {
        set(tag, (get(tag) ?: initialValue) + value)
    }
}