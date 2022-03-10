package com.github.zzehring.intellijjsonnet

import com.intellij.lang.Language

class JsonnetLanguage: Language("Jsonnet") {
    companion object {
        val INSTANCE = JsonnetLanguage()
    }
}