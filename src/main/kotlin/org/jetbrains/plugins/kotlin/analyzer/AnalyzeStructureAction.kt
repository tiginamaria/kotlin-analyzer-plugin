package org.jetbrains.plugins.kotlin.analyzer

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.util.Condition
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.psi.KtElement

class AnalyzeStructureAction : AnAction() {

    private val analyser = KotlinFileStructureAnalyzer()

    companion object {
        private val KT_FILE: Condition<VirtualFile> =
            Condition<VirtualFile> { f -> !f.isDirectory && f.extension == "kt" }
    }

    override fun update(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        if (project == null || project.isDefault) {
            print("Disable action: project was not provided")
            event.presentation.isEnabledAndVisible = false
            return
        }
        val file = event.getData(PlatformDataKeys.VIRTUAL_FILE)
        if (file == null || !KT_FILE.value(file)) {
            print("Disable action: only .kt files supported")
            event.presentation.isEnabledAndVisible = false
            return
        }
        event.presentation.isEnabledAndVisible = true
    }

    override fun actionPerformed(e: AnActionEvent) {
        val ktElement = e.getData(LangDataKeys.PSI_ELEMENT) as KtElement?
        if (ktElement == null) {
            print("Can not perform action: not file psi provided")
            return
        }
        analyser.process(ktElement)
    }
}
