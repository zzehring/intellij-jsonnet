// This is a generated file. Not intended for manual editing.
package com.github.zzehring.intellijjsonnet.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.zzehring.intellijjsonnet.psi.impl.*;

public interface JsonnetType {

  IElementType APPLY = new JsonnetElementType("APPLY");
  IElementType ARG = new JsonnetElementType("ARG");
  IElementType ARGS = new JsonnetElementType("ARGS");
  IElementType ARR = new JsonnetElementType("ARR");
  IElementType ARRCOMP = new JsonnetElementType("ARRCOMP");
  IElementType ASSERT_STMT = new JsonnetElementType("ASSERT_STMT");
  IElementType BINARYOP = new JsonnetElementType("BINARYOP");
  IElementType BIND = new JsonnetElementType("BIND");
  IElementType BINSUFFIX = new JsonnetElementType("BINSUFFIX");
  IElementType COMPSPEC = new JsonnetElementType("COMPSPEC");
  IElementType EXPR = new JsonnetElementType("EXPR");
  IElementType EXPR_0 = new JsonnetElementType("EXPR_0");
  IElementType FIELD = new JsonnetElementType("FIELD");
  IElementType FIELDNAME = new JsonnetElementType("FIELDNAME");
  IElementType FORSPEC = new JsonnetElementType("FORSPEC");
  IElementType H = new JsonnetElementType("H");
  IElementType IDENTIFIER_0 = new JsonnetElementType("IDENTIFIER_0");
  IElementType IFELSE = new JsonnetElementType("IFELSE");
  IElementType IFSPEC = new JsonnetElementType("IFSPEC");
  IElementType IMPORTOP = new JsonnetElementType("IMPORTOP");
  IElementType IMPORTSTROP = new JsonnetElementType("IMPORTSTROP");
  IElementType INSUPER = new JsonnetElementType("INSUPER");
  IElementType MEMBER = new JsonnetElementType("MEMBER");
  IElementType MEMBERS = new JsonnetElementType("MEMBERS");
  IElementType OBJ = new JsonnetElementType("OBJ");
  IElementType OBJEXTEND = new JsonnetElementType("OBJEXTEND");
  IElementType OBJINSIDE = new JsonnetElementType("OBJINSIDE");
  IElementType OBJLOCAL = new JsonnetElementType("OBJLOCAL");
  IElementType OUTERLOCAL = new JsonnetElementType("OUTERLOCAL");
  IElementType PARAM = new JsonnetElementType("PARAM");
  IElementType PARAMS = new JsonnetElementType("PARAMS");
  IElementType SELECT = new JsonnetElementType("SELECT");
  IElementType SLICE = new JsonnetElementType("SLICE");
  IElementType SLICESUFFIX = new JsonnetElementType("SLICESUFFIX");
  IElementType UNARYOP = new JsonnetElementType("UNARYOP");

  IElementType AND = new JsonnetTokenType("AND");
  IElementType ASSERT = new JsonnetTokenType("ASSERT");
  IElementType ASTERISK = new JsonnetTokenType("ASTERISK");
  IElementType BAR = new JsonnetTokenType("BAR");
  IElementType BLOCK_COMMENT = new JsonnetTokenType("BLOCK_COMMENT");
  IElementType CARAT = new JsonnetTokenType("CARAT");
  IElementType COLON = new JsonnetTokenType("COLON");
  IElementType COLON2 = new JsonnetTokenType("COLON2");
  IElementType COLON3 = new JsonnetTokenType("COLON3");
  IElementType COMMA = new JsonnetTokenType("COMMA");
  IElementType DOLLAR = new JsonnetTokenType("DOLLAR");
  IElementType DOT = new JsonnetTokenType("DOT");
  IElementType DOUBLE_AND = new JsonnetTokenType("DOUBLE_AND");
  IElementType DOUBLE_BAR = new JsonnetTokenType("DOUBLE_BAR");
  IElementType DOUBLE_EQUAL = new JsonnetTokenType("DOUBLE_EQUAL");
  IElementType DOUBLE_GREATER = new JsonnetTokenType("DOUBLE_GREATER");
  IElementType DOUBLE_LESS = new JsonnetTokenType("DOUBLE_LESS");
  IElementType DOUBLE_QUOTED_STRING = new JsonnetTokenType("DOUBLE_QUOTED_STRING");
  IElementType ELSE = new JsonnetTokenType("ELSE");
  IElementType EQUAL = new JsonnetTokenType("EQUAL");
  IElementType ERROR = new JsonnetTokenType("ERROR");
  IElementType EXCLAMATION = new JsonnetTokenType("EXCLAMATION");
  IElementType FALSE = new JsonnetTokenType("FALSE");
  IElementType FOR = new JsonnetTokenType("FOR");
  IElementType FUNCTION = new JsonnetTokenType("FUNCTION");
  IElementType GREATER_EQUAL = new JsonnetTokenType("GREATER_EQUAL");
  IElementType GREATER_THAN = new JsonnetTokenType("GREATER_THAN");
  IElementType IDENTIFIER = new JsonnetTokenType("IDENTIFIER");
  IElementType IF = new JsonnetTokenType("IF");
  IElementType IMPORT = new JsonnetTokenType("IMPORT");
  IElementType IMPORTSTR = new JsonnetTokenType("IMPORTSTR");
  IElementType IN = new JsonnetTokenType("IN");
  IElementType LESS_EQUAL = new JsonnetTokenType("LESS_EQUAL");
  IElementType LESS_THAN = new JsonnetTokenType("LESS_THAN");
  IElementType LINE_COMMENT = new JsonnetTokenType("LINE_COMMENT");
  IElementType LOCAL = new JsonnetTokenType("LOCAL");
  IElementType L_BRACKET = new JsonnetTokenType("L_BRACKET");
  IElementType L_CURLY = new JsonnetTokenType("L_CURLY");
  IElementType L_PAREN = new JsonnetTokenType("L_PAREN");
  IElementType MINUS = new JsonnetTokenType("MINUS");
  IElementType NOT_EQUAL = new JsonnetTokenType("NOT_EQUAL");
  IElementType NULL = new JsonnetTokenType("NULL");
  IElementType NUMBER = new JsonnetTokenType("NUMBER");
  IElementType PERCENT = new JsonnetTokenType("PERCENT");
  IElementType PLUS = new JsonnetTokenType("PLUS");
  IElementType R_BRACKET = new JsonnetTokenType("R_BRACKET");
  IElementType R_CURLY = new JsonnetTokenType("R_CURLY");
  IElementType R_PAREN = new JsonnetTokenType("R_PAREN");
  IElementType SELF = new JsonnetTokenType("SELF");
  IElementType SEMICOLON = new JsonnetTokenType("SEMICOLON");
  IElementType SINGLE_QUOTED_STRING = new JsonnetTokenType("SINGLE_QUOTED_STRING");
  IElementType SLASH = new JsonnetTokenType("SLASH");
  IElementType SUPER = new JsonnetTokenType("SUPER");
  IElementType THEN = new JsonnetTokenType("THEN");
  IElementType TILDE = new JsonnetTokenType("TILDE");
  IElementType TRIPLE_BAR_QUOTED_STRING = new JsonnetTokenType("TRIPLE_BAR_QUOTED_STRING");
  IElementType TRUE = new JsonnetTokenType("TRUE");
  IElementType VERBATIM_DOUBLE_QUOTED_STRING = new JsonnetTokenType("VERBATIM_DOUBLE_QUOTED_STRING");
  IElementType VERBATIM_SINGLE_QUOTED_STRING = new JsonnetTokenType("VERBATIM_SINGLE_QUOTED_STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == APPLY) {
        return new JsonnetApplyImpl(node);
      }
      else if (type == ARG) {
        return new JsonnetArgImpl(node);
      }
      else if (type == ARGS) {
        return new JsonnetArgsImpl(node);
      }
      else if (type == ARR) {
        return new JsonnetArrImpl(node);
      }
      else if (type == ARRCOMP) {
        return new JsonnetArrcompImpl(node);
      }
      else if (type == ASSERT_STMT) {
        return new JsonnetAssertStmtImpl(node);
      }
      else if (type == BINARYOP) {
        return new JsonnetBinaryopImpl(node);
      }
      else if (type == BIND) {
        return new JsonnetBindImpl(node);
      }
      else if (type == BINSUFFIX) {
        return new JsonnetBinsuffixImpl(node);
      }
      else if (type == COMPSPEC) {
        return new JsonnetCompspecImpl(node);
      }
      else if (type == EXPR) {
        return new JsonnetExprImpl(node);
      }
      else if (type == EXPR_0) {
        return new JsonnetExpr0Impl(node);
      }
      else if (type == FIELD) {
        return new JsonnetFieldImpl(node);
      }
      else if (type == FIELDNAME) {
        return new JsonnetFieldnameImpl(node);
      }
      else if (type == FORSPEC) {
        return new JsonnetForspecImpl(node);
      }
      else if (type == H) {
        return new JsonnetHImpl(node);
      }
      else if (type == IDENTIFIER_0) {
        return new JsonnetIdentifier0Impl(node);
      }
      else if (type == IFELSE) {
        return new JsonnetIfelseImpl(node);
      }
      else if (type == IFSPEC) {
        return new JsonnetIfspecImpl(node);
      }
      else if (type == IMPORTOP) {
        return new JsonnetImportopImpl(node);
      }
      else if (type == IMPORTSTROP) {
        return new JsonnetImportstropImpl(node);
      }
      else if (type == INSUPER) {
        return new JsonnetInsuperImpl(node);
      }
      else if (type == MEMBER) {
        return new JsonnetMemberImpl(node);
      }
      else if (type == MEMBERS) {
        return new JsonnetMembersImpl(node);
      }
      else if (type == OBJ) {
        return new JsonnetObjImpl(node);
      }
      else if (type == OBJEXTEND) {
        return new JsonnetObjextendImpl(node);
      }
      else if (type == OBJINSIDE) {
        return new JsonnetObjinsideImpl(node);
      }
      else if (type == OBJLOCAL) {
        return new JsonnetObjlocalImpl(node);
      }
      else if (type == OUTERLOCAL) {
        return new JsonnetOuterlocalImpl(node);
      }
      else if (type == PARAM) {
        return new JsonnetParamImpl(node);
      }
      else if (type == PARAMS) {
        return new JsonnetParamsImpl(node);
      }
      else if (type == SELECT) {
        return new JsonnetSelectImpl(node);
      }
      else if (type == SLICE) {
        return new JsonnetSliceImpl(node);
      }
      else if (type == SLICESUFFIX) {
        return new JsonnetSlicesuffixImpl(node);
      }
      else if (type == UNARYOP) {
        return new JsonnetUnaryopImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
