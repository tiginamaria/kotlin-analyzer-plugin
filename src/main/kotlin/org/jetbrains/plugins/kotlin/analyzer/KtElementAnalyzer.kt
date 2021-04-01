package org.jetbrains.plugins.kotlin.analyzer

import org.jetbrains.kotlin.psi.KtElement

class KotlinFileStructureAnalyzer {

    fun process(ktElement: KtElement) {
        val visitor = KtElementRecursiveVisitor()
        ktElement.accept(visitor)
    }
}