package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

class StatsStorage {
    private val statistics = mutableMapOf<String, Int>()

    fun getAll(): Map<String, Int> {
        return statistics
    }

    fun get(tag: String): Int? {
        return statistics[tag]
    }

    fun set(tag: String, value: Int) {
        statistics[tag] = value
    }

    fun inc(tag: String, value: Int, initialValue: Int = 0) {
        set(tag, get(tag) ?: initialValue + value)
    }
}