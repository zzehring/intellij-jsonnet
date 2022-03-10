// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.zzehring.intellijjsonnet.psi

import com.github.zzehring.intellijjsonnet.JsonnetLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class JsonnetTokenType(debugName: @NonNls String) :
    IElementType(debugName, JsonnetLanguage.INSTANCE) {
    override fun toString(): String {
        return "JsonnetTokenType." + super.toString()
    }
}