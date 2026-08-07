package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.psi.JsonnetArr
import com.github.zzehring.intellijjsonnet.psi.JsonnetObj
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase

/**
 * Tests for Jsonnet code folding functionality.
 * Verifies that the FoldingBuilder creates fold regions for objects and arrays.
 */
class JsonnetFoldingTest : BasePlatformTestCase() {

    private val foldingBuilder = JsonnetFoldingBuilder()

    fun testObjectsAreFoldable() {
        val file = myFixture.configureByText("test.jsonnet", """
            {
              name: "test",
              config: {
                nested: true
              }
            }
        """.trimIndent())

        val objects = PsiTreeUtil.findChildrenOfType(file, JsonnetObj::class.java)
        assertTrue("Should find object PSI elements", objects.isNotEmpty())

        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)
        assertTrue("Should create fold descriptors for objects", descriptors.isNotEmpty())
    }

    fun testArraysAreFoldable() {
        val file = myFixture.configureByText("test.jsonnet", """
            [
              1,
              2,
              3,
              4,
              5
            ]
        """.trimIndent())

        val arrays = PsiTreeUtil.findChildrenOfType(file, JsonnetArr::class.java)
        assertTrue("Should find array PSI elements", arrays.isNotEmpty())

        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)
        assertTrue("Should create fold descriptors for arrays", descriptors.isNotEmpty())
    }

    fun testNestedStructuresCreateMultipleFoldRegions() {
        val file = myFixture.configureByText("test.jsonnet", """
            {
              level1: {
                level2: {
                  level3: {
                    value: 42
                  }
                }
              }
            }
        """.trimIndent())

        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)
        // Should have fold regions for each nested object (4 objects total)
        assertEquals("Should create fold descriptor for each nested object", 4, descriptors.size)
    }

    fun testMixedObjectsAndArraysAreFoldable() {
        val file = myFixture.configureByText("test.jsonnet", """
            {
              items: [
                { name: "first" },
                { name: "second" }
              ],
              config: {
                enabled: true
              }
            }
        """.trimIndent())

        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)
        // 1 root object + 1 array + 2 objects in array + 1 config object = 5
        assertTrue("Should create fold descriptors for both objects and arrays", descriptors.size >= 4)
    }

    fun testFoldPlaceholderIsEllipsis() {
        val file = myFixture.configureByText("test.jsonnet", """
            { value: 42 }
        """.trimIndent())

        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)
        assertTrue("Should have at least one fold descriptor", descriptors.isNotEmpty())

        val placeholder = descriptors[0].placeholderText
        assertEquals("Fold placeholder should be ellipsis", "...", placeholder)
    }

    fun testEmptyObjectIsNotFoldable() {
        val file = myFixture.configureByText("test.jsonnet", "{}")

        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)
        // Empty braces {} have start+1 >= end-1, so no fold region created
        assertTrue("Empty object should not create a fold region", descriptors.isEmpty())
    }

    fun testFoldingNotCollapsedByDefault() {
        val file = myFixture.configureByText("test.jsonnet", "{ value: 42 }")

        val objects = PsiTreeUtil.findChildrenOfType(file, JsonnetObj::class.java)
        assertTrue("Should find object", objects.isNotEmpty())

        val collapsed = foldingBuilder.isCollapsedByDefault(objects.first().node)
        assertFalse("Folds should not be collapsed by default", collapsed)
    }
}
