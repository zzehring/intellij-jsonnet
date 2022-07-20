// This is a generated file. Not intended for manual editing.
package com.github.zzehring.intellijjsonnet.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zzehring.intellijjsonnet.psi.JsonnetTypes.*;
import com.github.zzehring.intellijjsonnet.psi.*;

public class JsonnetImportopImpl extends JsonnetNamedElementImpl implements JsonnetImportop {

  public JsonnetImportopImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsonnetVisitor visitor) {
    visitor.visitImportop(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsonnetVisitor) accept((JsonnetVisitor)visitor);
    else super.accept(visitor);
  }

}
