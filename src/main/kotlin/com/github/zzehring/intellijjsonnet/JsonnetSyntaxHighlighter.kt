package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.psi.JsonnetTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class JsonnetSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return JsonnetLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            JsonnetTypes.BLOCK_COMMENT -> {
                COMMENT_KEYS
            }
            JsonnetTypes.LINE_COMMENT -> {
                COMMENT_KEYS
            }
            JsonnetTypes.TRUE, JsonnetTypes.FALSE, JsonnetTypes.NULL, JsonnetTypes.IMPORT, JsonnetTypes.IMPORTSTR, JsonnetTypes.LOCAL, JsonnetTypes.FUNCTION, JsonnetTypes.IN, JsonnetTypes.IF, JsonnetTypes.THEN, JsonnetTypes.ELSE, JsonnetTypes.SUPER, JsonnetTypes.ERROR, JsonnetTypes.SELF, JsonnetTypes.FOR, JsonnetTypes.ASSERT, JsonnetTypes.DOLLAR -> {
                KEYWORD_KEYS
            }
            JsonnetTypes.NUMBER -> {
                NUMBER_KEYS
            }
            JsonnetTypes.SINGLE_QUOTED_STRING, JsonnetTypes.DOUBLE_QUOTED_STRING, JsonnetTypes.VERBATIM_DOUBLE_QUOTED_STRING, JsonnetTypes.VERBATIM_SINGLE_QUOTED_STRING, JsonnetTypes.TRIPLE_BAR_QUOTED_STRING -> {
                STRING_KEYS
            }
            TokenType.BAD_CHARACTER -> {
                BAD_CHAR_KEYS
            }
            else -> {
                EMPTY_KEYS
            }
        }
    }

    companion object {
        val BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey("JSONNET_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
        val KEY = TextAttributesKey.createTextAttributesKey("JSONNET_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val VALUE = TextAttributesKey.createTextAttributesKey("JSONNET_VALUE", DefaultLanguageHighlighterColors.STRING)
        val COMMENT =
            TextAttributesKey.createTextAttributesKey("JSONNET_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val NUMBER =
            TextAttributesKey.createTextAttributesKey("JSONNET_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEY)
        private val STRING_KEYS = arrayOf(VALUE)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
        private val NUMBER_KEYS = arrayOf(NUMBER)
    }
}