package org.jetbrains.plugins.kotlin.analyzer.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.Condition
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.plugins.kotlin.analyzer.actions.collectors.KtStatsCollector
import org.jetbrains.plugins.kotlin.analyzer.ui.StatsPathDialog

/**
 * Action triggered by button 'Collect Statistics' click in project menu panel file menu
 * (shown only for Kotlin classes).
 * Action perform [KtElement] types occurrence statistics collection.
 */
class CollectStatsAction : AnAction() {

    companion object {
        private val LOG = Logger.getInstance("org.jetbrains.plugins.kotlin.analyzer")
        private val KT_FILE: Condition<VirtualFile> =
            Condition<VirtualFile> { f -> !f.isDirectory && f.extension == "kt" }
    }

    /** Enable action visibility only on Kotlin files */
    override fun update(e: AnActionEvent) {
        val project = e.getData(PlatformDataKeys.PROJECT)
        if (project == null || project.isDefault) {
            LOG.warn("Disable action: project was not provided")
            e.presentation.isEnabledAndVisible = false
            return
        }
        val file = e.getData(PlatformDataKeys.VIRTUAL_FILE)
        if (file == null || !KT_FILE.value(file)) {
            LOG.warn("Disable action: only .kt files supported")
            e.presentation.isEnabledAndVisible = false
            return
        }
        e.presentation.isEnabledAndVisible = true
    }

    /** Execute [KtElement] types occurrence statistics collection on file provided by action */
    override fun actionPerformed(e: AnActionEvent) {
        val ktElement = e.getData(LangDataKeys.PSI_ELEMENT) as KtElement?
        if (ktElement == null) {
            LOG.error("Can not perform action: no kotlin file psi provided")
            return
        }
        val dialog = StatsPathDialog()
        if (dialog.showAndGet()) {
            ktElement.accept(KtStatsCollector(dialog.getPath()))
        }
    }
}
