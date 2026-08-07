package com.github.zzehring.intellijjsonnet

import com.intellij.testFramework.ParsingTestCase

/**
 * Tests that the Jsonnet parser correctly parses various Jsonnet constructs.
 * Uses IntelliJ's ParsingTestCase which compares parsed PSI trees against expected output.
 */
class JsonnetParsingTest : ParsingTestCase("", "jsonnet", JsonnetParserDefinition()) {

    override fun getTestDataPath(): String = "src/test/testData/parsing"

    override fun skipSpaces(): Boolean = true

    override fun includeRanges(): Boolean = true

    fun testSimpleObject() {
        doTest(true)
    }

    fun testNestedObject() {
        doTest(true)
    }

    fun testArray() {
        doTest(true)
    }

    fun testFunction() {
        doTest(true)
    }

    fun testLocalBinding() {
        doTest(true)
    }

    fun testImport() {
        doTest(true)
    }

    fun testArrayComprehension() {
        doTest(true)
    }

    fun testObjectComprehension() {
        doTest(true)
    }

    fun testConditional() {
        doTest(true)
    }

    fun testComments() {
        doTest(true)
    }
}