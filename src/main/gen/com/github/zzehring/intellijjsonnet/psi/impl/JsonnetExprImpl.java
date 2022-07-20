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

public class JsonnetExprImpl extends ASTWrapperPsiElement implements JsonnetExpr {

  public JsonnetExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsonnetVisitor visitor) {
    visitor.visitExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsonnetVisitor) accept((JsonnetVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JsonnetApply> getApplyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetApply.class);
  }

  @Override
  @NotNull
  public List<JsonnetBinsuffix> getBinsuffixList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetBinsuffix.class);
  }

  @Override
  @NotNull
  public JsonnetExpr0 getExpr0() {
    return findNotNullChildByClass(JsonnetExpr0.class);
  }

  @Override
  @NotNull
  public List<JsonnetInsuper> getInsuperList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetInsuper.class);
  }

  @Override
  @NotNull
  public List<JsonnetObjextend> getObjextendList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetObjextend.class);
  }

  @Override
  @NotNull
  public List<JsonnetSelect> getSelectList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetSelect.class);
  }

  @Override
  @NotNull
  public List<JsonnetSlice> getSliceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetSlice.class);
  }

}
