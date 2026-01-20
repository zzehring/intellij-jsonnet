package com.github.zzehring.intellijjsonnet

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.LanguageServerFactory
import com.redhat.devtools.lsp4ij.installation.ServerInstaller
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class JsonnetLanguageServerFactory : LanguageServerFactory {

    override fun createConnectionProvider(@NotNull project: Project): StreamConnectionProvider {
        return JsonnetLanguageServer(project)
    }

    /**
     * Creates a server installer for global scope installation.
     * The jsonnet-language-server binary is installed to ~/.lsp4ij/lsp/jsonnet-language-server/
     * and shared across all projects.
     */
    @Nullable
    override fun createServerInstaller(): ServerInstaller {
        return JsonnetServerInstaller()
    }
}
