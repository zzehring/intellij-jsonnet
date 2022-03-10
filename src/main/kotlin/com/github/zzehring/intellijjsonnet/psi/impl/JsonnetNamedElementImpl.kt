package com.github.zzehring.intellijjsonnet.psi.impl

import com.github.zzehring.intellijjsonnet.psi.JsonnetNamedElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry

abstract class JsonnetNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node),
    JsonnetNamedElement {
    override fun getReference(): PsiReference? {
        val referencesFromProviders = ReferenceProvidersRegistry.getReferencesFromProviders(this)
        return if (referencesFromProviders.size == 1) referencesFromProviders[0] else null
    }

    override fun getReferences(): Array<PsiReference> {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this)
    }
}