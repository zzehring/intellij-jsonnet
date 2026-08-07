package com.github.zzehring.intellijjsonnet

import com.intellij.codeInsight.highlighting.BraceMatchingUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase

/**
 * Tests for Jsonnet brace matching functionality.
 * Verifies that braces, brackets, and parentheses are properly matched.
 */
class JsonnetBraceMatchingTest : BasePlatformTestCase() {

    fun testCurlyBracesMatch() {
        myFixture.configureByText("test.jsonnet", """
            {<caret>
              value: 42
            }
        """.trimIndent())

        assertBraceBeforeCaretMatches("Opening brace should find matching closing brace")
    }

    fun testSquareBracketsMatch() {
        myFixture.configureByText("test.jsonnet", """
            [<caret>1, 2, 3]
        """.trimIndent())

        assertBraceBeforeCaretMatches("Opening bracket should find matching closing bracket")
    }

    fun testParenthesesMatch() {
        myFixture.configureByText("test.jsonnet", """
            local fn(<caret>x) = x * 2;
            fn(10)
        """.trimIndent())

        assertBraceBeforeCaretMatches("Opening parenthesis should find matching closing parenthesis")
    }

    fun testNestedBracesMatch() {
        myFixture.configureByText("test.jsonnet", """
            {<caret>
              outer: {
                inner: {
                  value: [1, 2, 3]
                }
              }
            }
        """.trimIndent())

        assertBraceBeforeCaretMatches("Opening brace should find matching closing brace in nested structure")
    }

    private fun assertBraceBeforeCaretMatches(message: String) {
        val offset = myFixture.caretOffset - 1
        val iterator = myFixture.editor.highlighter.createIterator(offset)

        val matched = BraceMatchingUtil.matchBrace(
            myFixture.editor.document.charsSequence,
            JsonnetFileType.INSTANCE,
            iterator,
            true
        )

        assertTrue(message, matched)
    }
}
