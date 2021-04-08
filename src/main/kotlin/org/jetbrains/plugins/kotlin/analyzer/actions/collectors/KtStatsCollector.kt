package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import com.intellij.openapi.diagnostic.Logger
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtVisitorVoid
import java.io.File

/**
 * The start and end point of statistics collection procedure.
 * Invoke statistics collection and dump tre results to give [statsDir].
 */
class KtStatsCollector(private val statsDir: String) : KtVisitorVoid() {

    companion object {
        private val LOG = Logger.getInstance("org.jetbrains.plugins.kotlin.analyzer")
    }

    /** If file contains only one [klass], redirect to [visitKtFile] */
    override fun visitClass(klass: KtClass) {
        visitKtFile(klass.containingKtFile)
    }

    /** Collect invoke collection of [KtElement] types occurrence statistics in [file] */
    override fun visitKtFile(file: KtFile) {
        LOG.info("Start processing file: ${file.virtualFile.name}")
        val statsStorage = StatsStorage()
        val ktTreeStatsCollector = KtTreeStatsCollector(statsStorage)
        file.accept(ktTreeStatsCollector)
        LOG.info("Finish processing file: ${file.virtualFile.name}")
        dumpStatsToCsv(file.virtualFile.nameWithoutExtension, statsStorage.getAll())
    }

    /** Dump [filename] file [stats] to csv format file and save to [statsDir] */
    private fun dumpStatsToCsv(filename: String, stats: Map<String, Int>) {
        val pathname = "$statsDir/${filename}Stats.csv"
        LOG.info("Start dumping results to file: $pathname")
        val file = File(pathname)
        file.createNewFile()
        file.printWriter().use {
            it.println(stats.keys.joinToString(","))
            it.println(stats.values.joinToString(","))
        }
        LOG.info("Finish dumping results to file: $pathname")
    }
}
