// This is a generated file. Not intended for manual editing.
package com.github.zzehring.intellijjsonnet.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsonnetExpr0 extends PsiElement {

  @Nullable
  JsonnetIdentifier0 getIdentifier0();

  @Nullable
  JsonnetArr getArr();

  @Nullable
  JsonnetArrcomp getArrcomp();

  @Nullable
  JsonnetAssertStmt getAssertStmt();

  @Nullable
  JsonnetExpr getExpr();

  @Nullable
  JsonnetIfelse getIfelse();

  @Nullable
  JsonnetImportop getImportop();

  @Nullable
  JsonnetImportstrop getImportstrop();

  @Nullable
  JsonnetObj getObj();

  @Nullable
  JsonnetOuterlocal getOuterlocal();

  @Nullable
  JsonnetParams getParams();

  @Nullable
  JsonnetUnaryop getUnaryop();

}
