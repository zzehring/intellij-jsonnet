package com.github.zzehring.intellijjsonnet

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.LanguageServerFactory
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider
import org.jetbrains.annotations.NotNull

class JsonnetLanguageServerFactory : LanguageServerFactory {

    override fun createConnectionProvider(@NotNull project: Project): StreamConnectionProvider {
        return JsonnetLanguageServer(project)
    }
}
