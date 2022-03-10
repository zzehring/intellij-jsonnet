package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.parser.JsonnetParser
import com.github.zzehring.intellijjsonnet.psi.JsonnetFile
import com.github.zzehring.intellijjsonnet.psi.JsonnetTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class JsonnetParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer {
        return JsonnetLexerAdapter()
    }

    override fun getCommentTokens(): TokenSet {
        return COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createParser(project: Project): PsiParser {
        return JsonnetParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return JsonnetFile(viewProvider)
    }

    override fun createElement(node: ASTNode): PsiElement {
        return JsonnetTypes.Factory.createElement(node)
    }

    companion object {
        val COMMENTS = TokenSet.create(JsonnetTypes.LINE_COMMENT, JsonnetTypes.BLOCK_COMMENT)
        val FILE = IFileElementType(JsonnetLanguage.INSTANCE)
    }
}