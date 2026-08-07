package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.psi.JsonnetFile
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase

/**
 * Tests for Jsonnet code insight features using the IntelliJ test framework.
 * These tests verify parsing, file type recognition, and PSI structure.
 */
class JsonnetCodeInsightTest : BasePlatformTestCase() {

    fun testFileTypeRecognition() {
        val jsonnetFile = myFixture.configureByText("test.jsonnet", "{}")
        assertInstanceOf(jsonnetFile, JsonnetFile::class.java)
        assertEquals(JsonnetFileType.INSTANCE, jsonnetFile.fileType)
    }

    fun testLibsonnetFileTypeRecognition() {
        val libsonnetFile = myFixture.configureByText("utils.libsonnet", "{}")
        assertInstanceOf(libsonnetFile, JsonnetFile::class.java)
        assertEquals(JsonnetFileType.INSTANCE, libsonnetFile.fileType)
    }

    fun testSimpleObjectParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            {
              name: "test",
              value: 42
            }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Simple object should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testLocalBindingParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            local x = 10;
            local add(a, b) = a + b;

            {
              result: add(x, 5)
            }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Local bindings should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testImportStatementParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            local lib = import 'library.libsonnet';
            local data = importstr 'data.txt';

            lib + { data: data }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Import statements should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testArrayComprehensionParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            [x * 2 for x in std.range(1, 10) if x % 2 == 0]
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Array comprehension should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testObjectComprehensionParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            {[key]: value for key in ["a", "b", "c"] for value in [1, 2, 3]}
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Object comprehension should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testConditionalExpressionParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            {
              value: if true then "yes" else "no",
              nested: if std.length([]) == 0 then { empty: true } else { empty: false }
            }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Conditional expressions should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testFunctionDefinitionParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            local makeConfig(name, port=8080, debug=false) = {
              name: name,
              port: port,
              debug: debug,
            };

            makeConfig("myapp", debug=true)
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Function definitions should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testObjectInheritanceParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            local base = {
              name: "base",
              value:: 10,  // hidden field
            };

            base + {
              name: "derived",
              computed: super.value * 2,
            }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Object inheritance should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testCommentsDoNotCauseErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            // Single line comment
            local x = 10; // trailing comment

            /*
               Multi-line
               block comment
            */
            {
              value: x /* inline comment */
            }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("Comments should not cause parse errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testStringLiteralsParsesWithoutErrors() {
        val file = myFixture.configureByText("test.jsonnet", """
            {
              single: 'single quoted',
              double: "double quoted",
              verbatim: @"verbatim string with \ no escapes",
              textBlock: |||
                Multi-line
                text block
              |||,
            }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertTrue("String literals should parse without errors, found: ${errors.map { it.errorDescription }}",
            errors.isEmpty())
    }

    fun testSyntaxErrorIsDetected() {
        val file = myFixture.configureByText("test.jsonnet", """
            {
              invalid syntax here :::
            }
        """.trimIndent())

        val errors = PsiTreeUtil.findChildrenOfType(file, PsiErrorElement::class.java)
        assertFalse("Syntax errors should be detected", errors.isEmpty())
    }
}
