package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtVisitorVoid
import java.io.File

class KtStatsCollector(val statsDir: String) : KtVisitorVoid() {

    override fun visitClass(klass: KtClass) {
        visitKtFile(klass.containingKtFile)
    }

    override fun visitKtFile(file: KtFile) {
        val statsStorage = StatsStorage()
        val ktTreeStatsCollector = KtTreeStatsCollector(statsStorage)
        file.accept(ktTreeStatsCollector)
        dumpStatsToCsv(file.virtualFile.nameWithoutExtension, statsStorage.getAll())
    }

    private fun dumpStatsToCsv(filename: String, stats: Map<String, Int>) {
        val file = File("$statsDir/${filename}Stat.csv")
        file.createNewFile()
        file.printWriter().use {
            it.println(stats.keys.joinToString(","))
            it.println(stats.values.joinToString(","))
        }
    }
}
