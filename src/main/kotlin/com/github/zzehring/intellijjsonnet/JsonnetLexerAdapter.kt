package com.github.zzehring.intellijjsonnet

import com.intellij.lexer.FlexAdapter

class JsonnetLexerAdapter : FlexAdapter(JsonnetLexer(null))