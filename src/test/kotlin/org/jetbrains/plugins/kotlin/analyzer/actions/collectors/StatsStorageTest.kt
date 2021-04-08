package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class StatsStorageTest {

    private lateinit var statsStorage: StatsStorage

    @Before
    fun setUp() {
        statsStorage = StatsStorage()
    }

    @Test
    fun getAll() {
        statsStorage.set("IF", 10)
        statsStorage.set("FUN", 20)
        statsStorage.inc("IF", 20)

        val stats = statsStorage.getAll()
        assertEquals(stats.size, 2)
        assertEquals(stats["IF"], 30)
        assertEquals(stats["FUN"], 20)
    }

    @Test
    fun getAndSet() {
        statsStorage.set("IF", 10)
        statsStorage.set("FUN", 20)
        statsStorage.inc("IF", 20)

        assertEquals(statsStorage.get("IF"), 30)
        assertEquals(statsStorage.get("FUN"), 20)
        assertNull(statsStorage.get("CLASS"))
    }

    @Test
    fun inc() {
        statsStorage.inc("IF", 10)
        assertEquals(statsStorage.get("IF"), 10)
        statsStorage.inc("IF", 10)
        assertEquals(statsStorage.get("IF"), 20)
        statsStorage.inc("FUN", 10)
        assertEquals(statsStorage.get("IF"), 20)
    }
}
