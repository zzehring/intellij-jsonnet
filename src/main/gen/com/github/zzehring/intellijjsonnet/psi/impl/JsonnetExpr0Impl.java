// This is a generated file. Not intended for manual editing.
package com.github.zzehring.intellijjsonnet.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zzehring.intellijjsonnet.psi.JsonnetTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.zzehring.intellijjsonnet.psi.*;

public class JsonnetExpr0Impl extends ASTWrapperPsiElement implements JsonnetExpr0 {

  public JsonnetExpr0Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsonnetVisitor visitor) {
    visitor.visitExpr0(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsonnetVisitor) accept((JsonnetVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JsonnetIdentifier0 getIdentifier0() {
    return findChildByClass(JsonnetIdentifier0.class);
  }

  @Override
  @Nullable
  public JsonnetArr getArr() {
    return findChildByClass(JsonnetArr.class);
  }

  @Override
  @Nullable
  public JsonnetArrcomp getArrcomp() {
    return findChildByClass(JsonnetArrcomp.class);
  }

  @Override
  @Nullable
  public JsonnetAssertStmt getAssertStmt() {
    return findChildByClass(JsonnetAssertStmt.class);
  }

  @Override
  @Nullable
  public JsonnetExpr getExpr() {
    return findChildByClass(JsonnetExpr.class);
  }

  @Override
  @Nullable
  public JsonnetIfelse getIfelse() {
    return findChildByClass(JsonnetIfelse.class);
  }

  @Override
  @Nullable
  public JsonnetImportop getImportop() {
    return findChildByClass(JsonnetImportop.class);
  }

  @Override
  @Nullable
  public JsonnetImportstrop getImportstrop() {
    return findChildByClass(JsonnetImportstrop.class);
  }

  @Override
  @Nullable
  public JsonnetObj getObj() {
    return findChildByClass(JsonnetObj.class);
  }

  @Override
  @Nullable
  public JsonnetOuterlocal getOuterlocal() {
    return findChildByClass(JsonnetOuterlocal.class);
  }

  @Override
  @Nullable
  public JsonnetParams getParams() {
    return findChildByClass(JsonnetParams.class);
  }

  @Override
  @Nullable
  public JsonnetUnaryop getUnaryop() {
    return findChildByClass(JsonnetUnaryop.class);
  }

}
