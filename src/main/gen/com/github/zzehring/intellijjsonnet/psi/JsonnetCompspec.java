// This is a generated file. Not intended for manual editing.
package com.github.zzehring.intellijjsonnet.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsonnetCompspec extends PsiElement {

  @NotNull
  List<JsonnetForspec> getForspecList();

  @NotNull
  List<JsonnetIfspec> getIfspecList();

}
