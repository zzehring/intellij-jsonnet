// This is a generated file. Not intended for manual editing.
package com.github.zzehring.intellijjsonnet;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.zzehring.intellijjsonnet.psi.JsonnetTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JsonnetParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean IDENTIFIER0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IDENTIFIER0")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, IDENTIFIER_0, r);
    return r;
  }

  /* ********************************************************** */
  // L_PAREN ( args )? R_PAREN
  public static boolean apply(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "apply")) return false;
    if (!nextTokenIs(b, L_PAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAREN);
    r = r && apply_1(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    exit_section_(b, m, APPLY, r);
    return r;
  }

  // ( args )?
  private static boolean apply_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "apply_1")) return false;
    apply_1_0(b, l + 1);
    return true;
  }

  // ( args )
  private static boolean apply_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "apply_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = args(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (IDENTIFIER EQUAL)? expr
  public static boolean arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARG, "<arg>");
    r = arg_0(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (IDENTIFIER EQUAL)?
  private static boolean arg_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_0")) return false;
    arg_0_0(b, l + 1);
    return true;
  }

  // IDENTIFIER EQUAL
  private static boolean arg_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQUAL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // arg ( COMMA arg )* ( COMMA )?
  public static boolean args(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGS, "<args>");
    r = arg(b, l + 1);
    r = r && args_1(b, l + 1);
    r = r && args_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( COMMA arg )*
  private static boolean args_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!args_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "args_1", c)) break;
    }
    return true;
  }

  // COMMA arg
  private static boolean args_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && arg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( COMMA )?
  private static boolean args_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // L_BRACKET (expr (COMMA expr)* COMMA?)? R_BRACKET
  public static boolean arr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && arr_1(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, ARR, r);
    return r;
  }

  // (expr (COMMA expr)* COMMA?)?
  private static boolean arr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr_1")) return false;
    arr_1_0(b, l + 1);
    return true;
  }

  // expr (COMMA expr)* COMMA?
  private static boolean arr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1);
    r = r && arr_1_0_1(b, l + 1);
    r = r && arr_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA expr)*
  private static boolean arr_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arr_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arr_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean arr_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean arr_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr_1_0_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // L_BRACKET expr COMMA? forspec compspec R_BRACKET
  public static boolean arrcomp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrcomp")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && arrcomp_2(b, l + 1);
    r = r && forspec(b, l + 1);
    r = r && compspec(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, ARRCOMP, r);
    return r;
  }

  // COMMA?
  private static boolean arrcomp_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrcomp_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // ASSERT expr ( COLON expr )?
  public static boolean assertStmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assertStmt")) return false;
    if (!nextTokenIs(b, ASSERT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSERT);
    r = r && expr(b, l + 1);
    r = r && assertStmt_2(b, l + 1);
    exit_section_(b, m, ASSERT_STMT, r);
    return r;
  }

  // ( COLON expr )?
  private static boolean assertStmt_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assertStmt_2")) return false;
    assertStmt_2_0(b, l + 1);
    return true;
  }

  // COLON expr
  private static boolean assertStmt_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assertStmt_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ASTERISK | SLASH | PERCENT | PLUS | MINUS | DOUBLE_LESS | DOUBLE_GREATER | LESS_THAN | LESS_EQUAL | GREATER_THAN | GREATER_EQUAL | DOUBLE_EQUAL | NOT_EQUAL | IN | AND | CARAT | BAR | DOUBLE_AND | DOUBLE_BAR
  public static boolean binaryop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binaryop")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINARYOP, "<binaryop>");
    r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, PERCENT);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, DOUBLE_LESS);
    if (!r) r = consumeToken(b, DOUBLE_GREATER);
    if (!r) r = consumeToken(b, LESS_THAN);
    if (!r) r = consumeToken(b, LESS_EQUAL);
    if (!r) r = consumeToken(b, GREATER_THAN);
    if (!r) r = consumeToken(b, GREATER_EQUAL);
    if (!r) r = consumeToken(b, DOUBLE_EQUAL);
    if (!r) r = consumeToken(b, NOT_EQUAL);
    if (!r) r = consumeToken(b, IN);
    if (!r) r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, CARAT);
    if (!r) r = consumeToken(b, BAR);
    if (!r) r = consumeToken(b, DOUBLE_AND);
    if (!r) r = consumeToken(b, DOUBLE_BAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER0 EQUAL expr
  // |	IDENTIFIER0 L_PAREN ( params )? R_PAREN EQUAL expr
  public static boolean bind(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bind_0(b, l + 1);
    if (!r) r = bind_1(b, l + 1);
    exit_section_(b, m, BIND, r);
    return r;
  }

  // IDENTIFIER0 EQUAL expr
  private static boolean bind_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IDENTIFIER0(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER0 L_PAREN ( params )? R_PAREN EQUAL expr
  private static boolean bind_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IDENTIFIER0(b, l + 1);
    r = r && consumeToken(b, L_PAREN);
    r = r && bind_1_2(b, l + 1);
    r = r && consumeTokens(b, 0, R_PAREN, EQUAL);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( params )?
  private static boolean bind_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_1_2")) return false;
    bind_1_2_0(b, l + 1);
    return true;
  }

  // ( params )
  private static boolean bind_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // binaryop expr
  public static boolean binsuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binsuffix")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINSUFFIX, "<binsuffix>");
    r = binaryop(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( forspec | ifspec )*
  public static boolean compspec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compspec")) return false;
    Marker m = enter_section_(b, l, _NONE_, COMPSPEC, "<compspec>");
    while (true) {
      int c = current_position_(b);
      if (!compspec_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "compspec", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // forspec | ifspec
  private static boolean compspec_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compspec_0")) return false;
    boolean r;
    r = forspec(b, l + 1);
    if (!r) r = ifspec(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // expr0 (select | slice | apply | binsuffix | objextend | insuper)*
  public static boolean expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR, "<expr>");
    r = expr0(b, l + 1);
    r = r && expr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (select | slice | apply | binsuffix | objextend | insuper)*
  private static boolean expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_1", c)) break;
    }
    return true;
  }

  // select | slice | apply | binsuffix | objextend | insuper
  private static boolean expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1_0")) return false;
    boolean r;
    r = select(b, l + 1);
    if (!r) r = slice(b, l + 1);
    if (!r) r = apply(b, l + 1);
    if (!r) r = binsuffix(b, l + 1);
    if (!r) r = objextend(b, l + 1);
    if (!r) r = insuper(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // NULL | TRUE | FALSE | SELF | DOLLAR
  // |   SINGLE_QUOTED_STRING
  // |   DOUBLE_QUOTED_STRING
  // |   VERBATIM_SINGLE_QUOTED_STRING
  // |   VERBATIM_DOUBLE_QUOTED_STRING
  // |   TRIPLE_BAR_QUOTED_STRING
  // |   NUMBER
  // |	obj
  // |	arr
  // |	arrcomp
  // |	SUPER DOT IDENTIFIER0
  // |	SUPER L_BRACKET expr R_BRACKET
  // |   outerlocal
  // |	ifelse
  // |	L_PAREN expr R_PAREN
  // |	unaryop expr
  // |	FUNCTION L_PAREN ( params )? R_PAREN expr
  // |	assertStmt SEMICOLON expr
  // |	importop
  // |	importstrop
  // |	ERROR expr
  // |	IDENTIFIER0
  public static boolean expr0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR_0, "<expr 0>");
    r = consumeToken(b, NULL);
    if (!r) r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    if (!r) r = consumeToken(b, SELF);
    if (!r) r = consumeToken(b, DOLLAR);
    if (!r) r = consumeToken(b, SINGLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, DOUBLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, VERBATIM_SINGLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, VERBATIM_DOUBLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, TRIPLE_BAR_QUOTED_STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = obj(b, l + 1);
    if (!r) r = arr(b, l + 1);
    if (!r) r = arrcomp(b, l + 1);
    if (!r) r = expr0_14(b, l + 1);
    if (!r) r = expr0_15(b, l + 1);
    if (!r) r = outerlocal(b, l + 1);
    if (!r) r = ifelse(b, l + 1);
    if (!r) r = expr0_18(b, l + 1);
    if (!r) r = expr0_19(b, l + 1);
    if (!r) r = expr0_20(b, l + 1);
    if (!r) r = expr0_21(b, l + 1);
    if (!r) r = importop(b, l + 1);
    if (!r) r = importstrop(b, l + 1);
    if (!r) r = expr0_24(b, l + 1);
    if (!r) r = IDENTIFIER0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SUPER DOT IDENTIFIER0
  private static boolean expr0_14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_14")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SUPER, DOT);
    r = r && IDENTIFIER0(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SUPER L_BRACKET expr R_BRACKET
  private static boolean expr0_15(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_15")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SUPER, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // L_PAREN expr R_PAREN
  private static boolean expr0_18(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_18")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAREN);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // unaryop expr
  private static boolean expr0_19(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_19")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unaryop(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FUNCTION L_PAREN ( params )? R_PAREN expr
  private static boolean expr0_20(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_20")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FUNCTION, L_PAREN);
    r = r && expr0_20_2(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( params )?
  private static boolean expr0_20_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_20_2")) return false;
    expr0_20_2_0(b, l + 1);
    return true;
  }

  // ( params )
  private static boolean expr0_20_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_20_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // assertStmt SEMICOLON expr
  private static boolean expr0_21(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_21")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = assertStmt(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ERROR expr
  private static boolean expr0_24(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_24")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ERROR);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // fieldname ( PLUS )? h expr
  // |	fieldname L_PAREN ( params )? R_PAREN h expr
  public static boolean field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD, "<field>");
    r = field_0(b, l + 1);
    if (!r) r = field_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // fieldname ( PLUS )? h expr
  private static boolean field_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fieldname(b, l + 1);
    r = r && field_0_1(b, l + 1);
    r = r && h(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( PLUS )?
  private static boolean field_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_0_1")) return false;
    consumeToken(b, PLUS);
    return true;
  }

  // fieldname L_PAREN ( params )? R_PAREN h expr
  private static boolean field_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fieldname(b, l + 1);
    r = r && consumeToken(b, L_PAREN);
    r = r && field_1_2(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    r = r && h(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( params )?
  private static boolean field_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_1_2")) return false;
    field_1_2_0(b, l + 1);
    return true;
  }

  // ( params )
  private static boolean field_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER0
  // | DOUBLE_QUOTED_STRING
  // | SINGLE_QUOTED_STRING
  // | VERBATIM_DOUBLE_QUOTED_STRING
  // | VERBATIM_SINGLE_QUOTED_STRING
  // | TRIPLE_BAR_QUOTED_STRING
  // | L_BRACKET expr R_BRACKET
  public static boolean fieldname(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldname")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELDNAME, "<fieldname>");
    r = IDENTIFIER0(b, l + 1);
    if (!r) r = consumeToken(b, DOUBLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, SINGLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, VERBATIM_DOUBLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, VERBATIM_SINGLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, TRIPLE_BAR_QUOTED_STRING);
    if (!r) r = fieldname_6(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // L_BRACKET expr R_BRACKET
  private static boolean fieldname_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldname_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FOR IDENTIFIER0 IN expr
  public static boolean forspec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forspec")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR);
    r = r && IDENTIFIER0(b, l + 1);
    r = r && consumeToken(b, IN);
    r = r && expr(b, l + 1);
    exit_section_(b, m, FORSPEC, r);
    return r;
  }

  /* ********************************************************** */
  // COLON | COLON2 | COLON3
  public static boolean h(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "h")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, H, "<h>");
    r = consumeToken(b, COLON);
    if (!r) r = consumeToken(b, COLON2);
    if (!r) r = consumeToken(b, COLON3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IF expr THEN expr ( ELSE expr )?
  public static boolean ifelse(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifelse")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, THEN);
    r = r && expr(b, l + 1);
    r = r && ifelse_4(b, l + 1);
    exit_section_(b, m, IFELSE, r);
    return r;
  }

  // ( ELSE expr )?
  private static boolean ifelse_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifelse_4")) return false;
    ifelse_4_0(b, l + 1);
    return true;
  }

  // ELSE expr
  private static boolean ifelse_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifelse_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IF expr
  public static boolean ifspec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifspec")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && expr(b, l + 1);
    exit_section_(b, m, IFSPEC, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT (DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING)
  public static boolean importop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importop")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && importop_1(b, l + 1);
    exit_section_(b, m, IMPORTOP, r);
    return r;
  }

  // DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING
  private static boolean importop_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importop_1")) return false;
    boolean r;
    r = consumeToken(b, DOUBLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, SINGLE_QUOTED_STRING);
    return r;
  }

  /* ********************************************************** */
  // IMPORTSTR (DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING)
  public static boolean importstrop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importstrop")) return false;
    if (!nextTokenIs(b, IMPORTSTR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORTSTR);
    r = r && importstrop_1(b, l + 1);
    exit_section_(b, m, IMPORTSTROP, r);
    return r;
  }

  // DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING
  private static boolean importstrop_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importstrop_1")) return false;
    boolean r;
    r = consumeToken(b, DOUBLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, SINGLE_QUOTED_STRING);
    return r;
  }

  /* ********************************************************** */
  // IN SUPER
  public static boolean insuper(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "insuper")) return false;
    if (!nextTokenIs(b, IN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IN, SUPER);
    exit_section_(b, m, INSUPER, r);
    return r;
  }

  /* ********************************************************** */
  // objlocal | assertStmt | field
  public static boolean member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MEMBER, "<member>");
    r = objlocal(b, l + 1);
    if (!r) r = assertStmt(b, l + 1);
    if (!r) r = field(b, l + 1);
    exit_section_(b, l, m, r, false, JsonnetParser::member_recover);
    return r;
  }

  /* ********************************************************** */
  // !('}')
  static boolean member_list_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_list_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !member_list_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ('}')
  private static boolean member_list_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_list_recover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "}");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(',' | '}')
  static boolean member_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !member_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ',' | '}'
  private static boolean member_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_recover_0")) return false;
    boolean r;
    r = consumeToken(b, ",");
    if (!r) r = consumeToken(b, "}");
    return r;
  }

  /* ********************************************************** */
  // member ( COMMA member )* ( COMMA )?
  public static boolean members(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "members")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MEMBERS, "<members>");
    r = member(b, l + 1);
    r = r && members_1(b, l + 1);
    r = r && members_2(b, l + 1);
    exit_section_(b, l, m, r, false, JsonnetParser::member_list_recover);
    return r;
  }

  // ( COMMA member )*
  private static boolean members_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "members_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!members_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "members_1", c)) break;
    }
    return true;
  }

  // COMMA member
  private static boolean members_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "members_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && member(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( COMMA )?
  private static boolean members_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "members_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // L_CURLY objinside? R_CURLY
  public static boolean obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "obj")) return false;
    if (!nextTokenIs(b, L_CURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_CURLY);
    r = r && obj_1(b, l + 1);
    r = r && consumeToken(b, R_CURLY);
    exit_section_(b, m, OBJ, r);
    return r;
  }

  // objinside?
  private static boolean obj_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "obj_1")) return false;
    objinside(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // L_CURLY objinside R_CURLY
  public static boolean objextend(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objextend")) return false;
    if (!nextTokenIs(b, L_CURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_CURLY);
    r = r && objinside(b, l + 1);
    r = r && consumeToken(b, R_CURLY);
    exit_section_(b, m, OBJEXTEND, r);
    return r;
  }

  /* ********************************************************** */
  // ( objlocal COMMA )* L_BRACKET expr R_BRACKET COLON expr ( ( COMMA objlocal )* ( COMMA )? )? forspec compspec
  // |	members
  public static boolean objinside(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJINSIDE, "<objinside>");
    r = objinside_0(b, l + 1);
    if (!r) r = members(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( objlocal COMMA )* L_BRACKET expr R_BRACKET COLON expr ( ( COMMA objlocal )* ( COMMA )? )? forspec compspec
  private static boolean objinside_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = objinside_0_0(b, l + 1);
    r = r && consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && consumeTokens(b, 0, R_BRACKET, COLON);
    r = r && expr(b, l + 1);
    r = r && objinside_0_6(b, l + 1);
    r = r && forspec(b, l + 1);
    r = r && compspec(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( objlocal COMMA )*
  private static boolean objinside_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!objinside_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "objinside_0_0", c)) break;
    }
    return true;
  }

  // objlocal COMMA
  private static boolean objinside_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = objlocal(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( ( COMMA objlocal )* ( COMMA )? )?
  private static boolean objinside_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_6")) return false;
    objinside_0_6_0(b, l + 1);
    return true;
  }

  // ( COMMA objlocal )* ( COMMA )?
  private static boolean objinside_0_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = objinside_0_6_0_0(b, l + 1);
    r = r && objinside_0_6_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( COMMA objlocal )*
  private static boolean objinside_0_6_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_6_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!objinside_0_6_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "objinside_0_6_0_0", c)) break;
    }
    return true;
  }

  // COMMA objlocal
  private static boolean objinside_0_6_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_6_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && objlocal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( COMMA )?
  private static boolean objinside_0_6_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_6_0_1")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // LOCAL bind
  public static boolean objlocal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objlocal")) return false;
    if (!nextTokenIs(b, LOCAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LOCAL);
    r = r && bind(b, l + 1);
    exit_section_(b, m, OBJLOCAL, r);
    return r;
  }

  /* ********************************************************** */
  // LOCAL bind ( COMMA bind )* SEMICOLON expr
  public static boolean outerlocal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "outerlocal")) return false;
    if (!nextTokenIs(b, LOCAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LOCAL);
    r = r && bind(b, l + 1);
    r = r && outerlocal_2(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    r = r && expr(b, l + 1);
    exit_section_(b, m, OUTERLOCAL, r);
    return r;
  }

  // ( COMMA bind )*
  private static boolean outerlocal_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "outerlocal_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!outerlocal_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "outerlocal_2", c)) break;
    }
    return true;
  }

  // COMMA bind
  private static boolean outerlocal_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "outerlocal_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && bind(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER0 (EQUAL expr)?
  public static boolean param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IDENTIFIER0(b, l + 1);
    r = r && param_1(b, l + 1);
    exit_section_(b, m, PARAM, r);
    return r;
  }

  // (EQUAL expr)?
  private static boolean param_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_1")) return false;
    param_1_0(b, l + 1);
    return true;
  }

  // EQUAL expr
  private static boolean param_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // param (COMMA param)* ( COMMA )?
  public static boolean params(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = param(b, l + 1);
    r = r && params_1(b, l + 1);
    r = r && params_2(b, l + 1);
    exit_section_(b, m, PARAMS, r);
    return r;
  }

  // (COMMA param)*
  private static boolean params_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!params_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "params_1", c)) break;
    }
    return true;
  }

  // COMMA param
  private static boolean params_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( COMMA )?
  private static boolean params_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // expr
  static boolean root(PsiBuilder b, int l) {
    return expr(b, l + 1);
  }

  /* ********************************************************** */
  // DOT IDENTIFIER0
  public static boolean select(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "select")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && IDENTIFIER0(b, l + 1);
    exit_section_(b, m, SELECT, r);
    return r;
  }

  /* ********************************************************** */
  // L_BRACKET expr? slicesuffix? R_BRACKET
  public static boolean slice(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && slice_1(b, l + 1);
    r = r && slice_2(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, SLICE, r);
    return r;
  }

  // expr?
  private static boolean slice_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_1")) return false;
    expr(b, l + 1);
    return true;
  }

  // slicesuffix?
  private static boolean slice_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2")) return false;
    slicesuffix(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COLON ( expr? ( COLON expr? )? )? | COLON2 expr?
  public static boolean slicesuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix")) return false;
    if (!nextTokenIs(b, "<slicesuffix>", COLON, COLON2)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SLICESUFFIX, "<slicesuffix>");
    r = slicesuffix_0(b, l + 1);
    if (!r) r = slicesuffix_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COLON ( expr? ( COLON expr? )? )?
  private static boolean slicesuffix_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && slicesuffix_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( expr? ( COLON expr? )? )?
  private static boolean slicesuffix_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_0_1")) return false;
    slicesuffix_0_1_0(b, l + 1);
    return true;
  }

  // expr? ( COLON expr? )?
  private static boolean slicesuffix_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = slicesuffix_0_1_0_0(b, l + 1);
    r = r && slicesuffix_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expr?
  private static boolean slicesuffix_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_0_1_0_0")) return false;
    expr(b, l + 1);
    return true;
  }

  // ( COLON expr? )?
  private static boolean slicesuffix_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_0_1_0_1")) return false;
    slicesuffix_0_1_0_1_0(b, l + 1);
    return true;
  }

  // COLON expr?
  private static boolean slicesuffix_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && slicesuffix_0_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expr?
  private static boolean slicesuffix_0_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_0_1_0_1_0_1")) return false;
    expr(b, l + 1);
    return true;
  }

  // COLON2 expr?
  private static boolean slicesuffix_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON2);
    r = r && slicesuffix_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expr?
  private static boolean slicesuffix_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slicesuffix_1_1")) return false;
    expr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MINUS | PLUS | EXCLAMATION | TILDE
  public static boolean unaryop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryop")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNARYOP, "<unaryop>");
    r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, EXCLAMATION);
    if (!r) r = consumeToken(b, TILDE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
