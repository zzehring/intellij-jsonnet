package com.github.zzehring.intellijjsonnet

import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.InitializeResult
import org.eclipse.lsp4j.services.LanguageServer
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.ServerListener

const val EXTENSIONS: String = "jsonnet,libsonnet"

class JsonnetLSDefinition(command: Array<String?>?, private val initialSettings: Any) :
    RawCommandServerDefinition(
        EXTENSIONS, command
    ) {
    override fun getServerListener(): ServerListener {
        return object : ServerListener {
            override fun initialize(server: LanguageServer, result: InitializeResult) {
                server.workspaceService.didChangeConfiguration(DidChangeConfigurationParams(initialSettings))
            }
        }
    }
}

