package org.jetbrains.plugins.kotlin.analyzer

import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid

class KtElementRecursiveVisitor: KtTreeVisitorVoid() {

    override fun visitKtElement(element: KtElement) {
        println(element)
        super.visitKtElement(element)
    }
}