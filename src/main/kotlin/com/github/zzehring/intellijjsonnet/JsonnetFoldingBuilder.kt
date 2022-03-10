package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.psi.JsonnetArr
import com.github.zzehring.intellijjsonnet.psi.JsonnetArrcomp
import com.github.zzehring.intellijjsonnet.psi.JsonnetObj
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class JsonnetFoldingBuilder() : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        val literalExpressions = PsiTreeUtil.findChildrenOfType<PsiElement>(
            root,
            JsonnetObj::class.java
        )
        literalExpressions.addAll(PsiTreeUtil.findChildrenOfType(root, JsonnetArr::class.java))
        literalExpressions.addAll(PsiTreeUtil.findChildrenOfType(root, JsonnetArrcomp::class.java))
        for (literalExpression: PsiElement in literalExpressions) {
            val group = FoldingGroup.newGroup(
                "jsonnet-" + literalExpression.textRange.startOffset +
                        "-" + literalExpression.textRange.endOffset
            )
            val start = literalExpression.textRange.startOffset + 1
            val end = literalExpression.textRange.endOffset - 1
            if (end > start) descriptors.add(
                object : FoldingDescriptor(
                    literalExpression.node,
                    TextRange(start, end),
                    group
                ) {
                    override fun getPlaceholderText(): String? {
                        return "..."
                    }
                }
            )
        }
        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        return "..."
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return false
    }
}