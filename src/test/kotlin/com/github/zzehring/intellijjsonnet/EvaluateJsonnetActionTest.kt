package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.actions.EvaluateJsonnetAction
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.testFramework.fixtures.BasePlatformTestCase

/**
 * Tests for the EvaluateJsonnetAction.
 * Verifies that the action is enabled/visible only for .jsonnet files.
 */
class EvaluateJsonnetActionTest : BasePlatformTestCase() {

    private val action = EvaluateJsonnetAction()

    fun testActionEnabledForJsonnetFiles() {
        val file = myFixture.configureByText("test.jsonnet", "{ value: 42 }")
        val event = createActionEvent(file.virtualFile)

        action.update(event)

        assertTrue("Action should be enabled for .jsonnet files", event.presentation.isEnabledAndVisible)
    }

    fun testActionEnabledForLibsonnetFiles() {
        // Note: The action currently only checks for "jsonnet" extension, not "libsonnet"
        // This test documents the current behavior
        val file = myFixture.configureByText("utils.libsonnet", "{ helper:: fn(x) = x }")
        val event = createActionEvent(file.virtualFile)

        action.update(event)

        // Current implementation only enables for .jsonnet extension
        assertFalse("Action is not enabled for .libsonnet files (current behavior)",
            event.presentation.isEnabledAndVisible)
    }

    fun testActionDisabledForNonJsonnetFiles() {
        val file = myFixture.configureByText("config.json", """{"value": 42}""")
        val event = createActionEvent(file.virtualFile)

        action.update(event)

        assertFalse("Action should be disabled for non-jsonnet files", event.presentation.isEnabledAndVisible)
    }

    fun testActionDisabledForTextFiles() {
        val file = myFixture.configureByText("readme.txt", "Some text content")
        val event = createActionEvent(file.virtualFile)

        action.update(event)

        assertFalse("Action should be disabled for text files", event.presentation.isEnabledAndVisible)
    }

    fun testActionDisabledWhenNoFileOpen() {
        val event = createActionEvent(null)

        action.update(event)

        assertFalse("Action should be disabled when no file is open", event.presentation.isEnabledAndVisible)
    }

    fun testActionUpdateThreadIsBGT() {
        val updateThread = action.actionUpdateThread

        assertEquals("Action update should run on background thread",
            ActionUpdateThread.BGT, updateThread)
    }

    private fun createActionEvent(virtualFile: com.intellij.openapi.vfs.VirtualFile?): AnActionEvent {
        val dataContext = object : DataContext {
            override fun getData(dataId: String): Any? {
                return when (dataId) {
                    PlatformDataKeys.VIRTUAL_FILE.name -> virtualFile
                    CommonDataKeys.PROJECT.name -> project
                    else -> null
                }
            }
        }

        return AnActionEvent.createFromDataContext(
            "TestAction",
            Presentation(),
            dataContext
        )
    }
}
