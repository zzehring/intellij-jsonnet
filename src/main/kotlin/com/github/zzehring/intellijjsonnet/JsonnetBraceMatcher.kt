package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.psi.JsonnetTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class JsonnetBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> {
        return PAIRS
    }

    override fun isPairedBracesAllowedBeforeType(type: IElementType, tokenType: IElementType?): Boolean {
        return false
    }

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int {
        return openingBraceOffset
    }

    companion object {
        private val PAIRS = arrayOf(
            BracePair(JsonnetTypes.L_BRACKET, JsonnetTypes.R_BRACKET, false),
            BracePair(JsonnetTypes.L_PAREN, JsonnetTypes.R_PAREN, false),
            BracePair(JsonnetTypes.L_CURLY, JsonnetTypes.R_CURLY, true)
        )
    }
}