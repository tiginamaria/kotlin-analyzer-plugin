package org.jetbrains.plugins.kotlin.analyzer.ui

import com.intellij.openapi.ui.DialogWrapper
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JFileChooser
import javax.swing.JPanel

class StatsPathDialog(currentPath: String? = null) : DialogWrapper(true) {

    private val panel = JPanel(GridBagLayout())
    private val fileChooser: JFileChooser = JFileChooser(currentPath ?: System.getProperty("user.dir"))

    init {
        title = "Select directory to save statistics"
        fileChooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        init()
    }

    fun getPath(): String {
        return fileChooser.selectedFile.path
    }

    override fun createCenterPanel(): JComponent {
        panel.add(fileChooser)

        return panel
    }
}