package com.github.zzehring.intellijjsonnet.psi

import com.github.zzehring.intellijjsonnet.JsonnetFileType
import com.github.zzehring.intellijjsonnet.JsonnetLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class JsonnetFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, JsonnetLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return JsonnetFileType.INSTANCE
    }

    override fun toString(): String {
        return "Jsonnet File"
    }
}