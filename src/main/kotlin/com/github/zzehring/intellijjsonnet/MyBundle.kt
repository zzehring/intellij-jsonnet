package com.github.zzehring.intellijjsonnet

import com.intellij.DynamicBundle
import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.progress.ProgressIndicator
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition

@NonNls
private const val BUNDLE = "messages.MyBundle"

object MyBundle : DynamicBundle(BUNDLE) {

    @Suppress("SpreadOperator")
    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        getMessage(key, *params)

    @Suppress("SpreadOperator", "unused")
    @JvmStatic
    fun messagePointer(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        getLazyMessage(key, *params)
}

object JsonnetLSPreloadingActivity : PreloadingActivity() {
    override fun preload(indicator: ProgressIndicator) {
        IntellijLanguageClient.addServerDefinition(RawCommandServerDefinition(
            "jsonnet,libsonnet",
            arrayOf("/Users/zzehring/work/projects/grafana_org/hackathon/jsonnet-language-server/jsonnet-language-server")))
    }

}
