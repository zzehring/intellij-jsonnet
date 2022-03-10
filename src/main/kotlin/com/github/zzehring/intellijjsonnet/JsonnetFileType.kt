package com.github.zzehring.intellijjsonnet

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class JsonnetFileType : LanguageFileType(JsonnetLanguage.INSTANCE) {
    override fun getName(): String {
        return "Jsonnet File"
    }

    override fun getDescription(): String {
        return "Jsonnet language file"
    }

    override fun getDefaultExtension(): String {
        return "jsonnet"
    }

    override fun getIcon(): Icon {
        return JsonnetIcons.FILE
    }

    companion object {
        val INSTANCE = JsonnetFileType()
    }
}