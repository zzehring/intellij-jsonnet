package com.github.zzehring.intellijjsonnet

import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction
import com.intellij.codeInsight.generation.actions.CommentByBlockCommentAction
import com.intellij.testFramework.fixtures.BasePlatformTestCase

/**
 * Tests for Jsonnet commenting functionality.
 * Verifies that line and block comments work correctly.
 */
class JsonnetCommenterTest : BasePlatformTestCase() {

    fun testLineCommentAction() {
        myFixture.configureByText("test.jsonnet", """
            {
              <caret>value: 42
            }
        """.trimIndent())

        CommentByLineCommentAction().actionPerformedImpl(project, myFixture.editor)

        val commentedLine = myFixture.editor.document.text.lines().first { it.contains("value: 42") }
        assertTrue("Line should be commented with //", commentedLine.trimStart().startsWith("//"))
    }

    fun testLineUncommentAction() {
        myFixture.configureByText("test.jsonnet", """
            {
              <caret>// value: 42
            }
        """.trimIndent())

        CommentByLineCommentAction().actionPerformedImpl(project, myFixture.editor)

        val text = myFixture.editor.document.text
        assertTrue("Line should be uncommented", text.contains("value: 42") && !text.contains("// value"))
    }

    fun testBlockCommentAction() {
        myFixture.configureByText("test.jsonnet", """
            {
              <selection>name: "test",
              value: 42</selection>
            }
        """.trimIndent())

        CommentByBlockCommentAction().actionPerformedImpl(project, myFixture.editor)

        val text = myFixture.editor.document.text
        assertTrue("Selection should be wrapped in block comment /* */",
            text.contains("/*") && text.contains("*/"))
    }

    fun testCommenterPrefixes() {
        val commenter = SimpleCommenter()

        assertEquals("Line comment prefix should be //", "//", commenter.lineCommentPrefix)
        assertEquals("Block comment prefix should be /*", "/*", commenter.blockCommentPrefix)
        assertEquals("Block comment suffix should be */", "*/", commenter.blockCommentSuffix)
    }

    fun testMultipleLineComments() {
        myFixture.configureByText("test.jsonnet", """
            {
              <selection>name: "test",
              value: 42,
              enabled: true</selection>
            }
        """.trimIndent())

        CommentByLineCommentAction().actionPerformedImpl(project, myFixture.editor)

        val text = myFixture.editor.document.text
        val lineCommentCount = text.lines().count { it.trim().startsWith("//") }
        assertTrue("Multiple lines should be commented", lineCommentCount >= 3)
    }
}
