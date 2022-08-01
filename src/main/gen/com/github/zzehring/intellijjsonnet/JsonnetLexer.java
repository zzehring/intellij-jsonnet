/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

// This code was taken from https://github.com/databricks/intellij-jsonnet/blob/master/src/main/grammars/Jsonnet.flex
package com.github.zzehring.intellijjsonnet;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.github.zzehring.intellijjsonnet.psi.JsonnetTypes.*;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import com.intellij.psi.TokenType;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>Jsonnet.flex</tt>
 */
class JsonnetLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [7, 7, 7]
   * Total runtime size is 1928 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>14]|((ch>>7)&0x7f)]<<7)|(ch&0x7f)];
  }

  /* The ZZ_CMAP_Z table has 68 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\103\200");

  /* The ZZ_CMAP_Y table has 256 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\53\2\1\3\22\2\1\4\37\2\1\3\237\2");

  /* The ZZ_CMAP_A table has 640 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\1\1\3\2\1\1\10\22\0\1\1\1\61\1\6\1\4\1\66\1\56\1\62\1\11\1\37\1\40"+
    "\1\5\1\17\1\41\1\26\1\15\1\2\1\13\11\14\1\44\1\42\1\57\1\43\1\60\1\0\1\12"+
    "\4\32\1\16\3\32\1\20\4\32\1\27\14\32\1\35\1\7\1\36\1\63\1\32\1\0\1\30\1\32"+
    "\1\55\1\32\1\47\1\22\1\32\1\65\1\23\2\32\1\50\1\52\1\21\1\54\1\53\1\32\1\45"+
    "\1\51\1\24\1\46\3\32\1\25\1\32\1\33\1\31\1\34\1\64\6\0\1\1\32\0\1\1\337\0"+
    "\1\1\177\0\13\1\35\0\2\1\5\0\1\1\57\0\1\1\40\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\1\2\10\1\11\1\12\1\13\5\12\1\14\2\12"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\3\12\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\6\2\0\1\7"+
    "\1\41\1\42\1\0\1\10\5\12\1\43\1\44\3\12"+
    "\1\0\2\12\1\45\1\46\1\47\5\12\1\50\1\51"+
    "\1\52\1\53\1\54\1\55\1\0\1\41\1\42\2\10"+
    "\4\12\1\56\3\12\1\0\1\10\1\12\1\0\1\57"+
    "\5\12\1\40\1\12\1\60\3\12\1\61\1\62\1\0"+
    "\1\12\1\0\1\12\1\63\2\12\1\64\1\12\1\65"+
    "\2\12\1\0\1\12\1\0\1\66\1\67\1\70\2\12"+
    "\1\71\1\0\1\72\1\0\3\12\2\0\1\73\1\12"+
    "\1\0\1\74\1\75\1\10";

  private static int [] zzUnpackAction() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\67\0\156\0\245\0\334\0\67\0\u0113\0\u014a"+
    "\0\u0181\0\u01b8\0\u01ef\0\67\0\u0226\0\67\0\u025d\0\u0294"+
    "\0\u02cb\0\u0302\0\u0339\0\u0370\0\u03a7\0\u03de\0\u0415\0\67"+
    "\0\67\0\67\0\67\0\67\0\67\0\67\0\67\0\u044c"+
    "\0\u0483\0\u04ba\0\u04f1\0\u0528\0\67\0\u055f\0\u0596\0\u05cd"+
    "\0\u0604\0\67\0\67\0\67\0\u063b\0\67\0\u0672\0\u06a9"+
    "\0\67\0\u06e0\0\u0717\0\u074e\0\u0785\0\u07bc\0\u07f3\0\u082a"+
    "\0\u0861\0\u0898\0\u0226\0\u0226\0\u08cf\0\u0906\0\u093d\0\u0974"+
    "\0\u09ab\0\u09e2\0\u0a19\0\67\0\u0a50\0\u0a87\0\u0abe\0\u0af5"+
    "\0\u0b2c\0\u0b63\0\67\0\67\0\67\0\67\0\67\0\67"+
    "\0\u0b9a\0\u0bd1\0\u0c08\0\u0c3f\0\u0c76\0\u0cad\0\u0ce4\0\u0d1b"+
    "\0\u0d52\0\u0226\0\u0d89\0\u0dc0\0\u0df7\0\u0e2e\0\u0226\0\u0e65"+
    "\0\u0e9c\0\67\0\u0ed3\0\u0f0a\0\u0f41\0\u0f78\0\u0faf\0\67"+
    "\0\u0fe6\0\u0226\0\u101d\0\u1054\0\u108b\0\u0226\0\u0226\0\u10c2"+
    "\0\u10f9\0\u1130\0\u1167\0\u0226\0\u119e\0\u11d5\0\u0226\0\u120c"+
    "\0\u0226\0\u1243\0\u127a\0\u12b1\0\u12e8\0\u131f\0\u0226\0\u0226"+
    "\0\u0226\0\u1356\0\u138d\0\u13c4\0\u13fb\0\u0226\0\u1432\0\u1469"+
    "\0\u14a0\0\u14d7\0\u150e\0\u1545\0\u0226\0\u157c\0\u15b3\0\67"+
    "\0\u0226\0\67";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\3\1\5\1\6\1\7\1\2"+
    "\1\3\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\15\1\24\1\25"+
    "\1\26\1\27\1\15\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\2\15\1\42\1\43"+
    "\1\44\4\15\1\45\1\46\1\47\1\50\1\51\1\52"+
    "\1\53\1\15\1\54\70\0\1\3\1\0\1\3\4\0"+
    "\1\3\60\0\1\5\2\0\1\55\61\0\3\5\1\0"+
    "\63\5\6\7\1\56\1\57\57\7\7\10\1\60\1\10"+
    "\1\61\55\10\6\0\1\62\2\0\1\63\72\0\1\64"+
    "\1\65\30\0\1\65\32\0\2\13\1\64\1\65\30\0"+
    "\1\65\32\0\2\15\1\0\1\15\1\0\6\15\1\0"+
    "\2\15\1\0\1\15\12\0\11\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\1\15\1\66\4\15\1\0"+
    "\2\15\1\0\1\15\12\0\11\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\6\15\1\0\2\15\1\0"+
    "\1\15\12\0\1\15\1\67\7\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\6\15\1\0\1\15\1\70"+
    "\1\0\1\15\12\0\1\15\1\71\5\15\1\72\1\15"+
    "\7\0\1\15\14\0\2\15\1\0\1\15\1\0\1\15"+
    "\1\73\1\74\3\15\1\0\2\15\1\0\1\15\12\0"+
    "\5\15\1\75\3\15\7\0\1\15\14\0\2\15\1\0"+
    "\1\15\1\0\6\15\1\0\2\15\1\0\1\15\12\0"+
    "\1\76\10\15\7\0\1\77\21\0\1\100\61\0\2\15"+
    "\1\0\1\15\1\0\6\15\1\0\1\15\1\101\1\0"+
    "\1\15\12\0\11\15\7\0\1\15\14\0\2\15\1\0"+
    "\1\15\1\0\6\15\1\0\2\15\1\0\1\15\12\0"+
    "\4\15\1\102\4\15\7\0\1\15\32\0\1\103\100\0"+
    "\1\104\67\0\1\105\35\0\2\15\1\0\1\15\1\0"+
    "\6\15\1\0\2\15\1\0\1\15\12\0\1\106\2\15"+
    "\1\107\5\15\7\0\1\15\14\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\7\15"+
    "\1\110\1\15\7\0\1\15\14\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\1\15"+
    "\1\111\1\112\6\15\7\0\1\15\44\0\1\113\13\0"+
    "\1\114\52\0\1\115\14\0\1\116\51\0\1\117\105\0"+
    "\1\120\4\0\5\55\1\121\61\55\3\7\1\0\4\7"+
    "\1\0\56\7\3\10\1\0\4\10\1\0\56\10\6\62"+
    "\1\122\60\62\11\63\1\123\55\63\13\0\2\124\65\0"+
    "\2\125\2\0\1\125\6\0\1\125\53\0\2\15\1\0"+
    "\1\15\1\0\2\15\1\126\3\15\1\0\2\15\1\0"+
    "\1\15\12\0\11\15\7\0\1\15\14\0\2\15\1\0"+
    "\1\15\1\0\6\15\1\0\2\15\1\0\1\15\12\0"+
    "\3\15\1\127\5\15\7\0\1\15\14\0\2\15\1\0"+
    "\1\15\1\0\6\15\1\0\2\15\1\0\1\15\12\0"+
    "\3\15\1\130\5\15\7\0\1\15\14\0\2\15\1\0"+
    "\1\15\1\0\1\15\1\131\4\15\1\0\2\15\1\0"+
    "\1\15\12\0\11\15\7\0\1\15\14\0\2\15\1\0"+
    "\1\15\1\0\6\15\1\0\2\15\1\0\1\15\12\0"+
    "\1\132\10\15\7\0\1\15\14\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\6\15"+
    "\1\133\2\15\7\0\1\15\14\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\1\15"+
    "\1\134\7\15\7\0\1\15\14\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\2\15"+
    "\1\135\6\15\7\0\1\15\22\0\1\136\60\0\2\15"+
    "\1\0\1\15\1\0\6\15\1\0\1\137\1\15\1\0"+
    "\1\15\12\0\11\15\7\0\1\15\14\0\2\15\1\0"+
    "\1\15\1\0\6\15\1\0\2\15\1\0\1\15\12\0"+
    "\4\15\1\140\4\15\7\0\1\15\32\0\1\141\101\0"+
    "\1\142\35\0\2\15\1\0\1\15\1\0\6\15\1\0"+
    "\2\15\1\0\1\15\12\0\1\143\10\15\7\0\1\15"+
    "\14\0\2\15\1\0\1\15\1\0\6\15\1\0\2\15"+
    "\1\0\1\15\12\0\4\15\1\144\4\15\7\0\1\15"+
    "\14\0\2\15\1\0\1\15\1\0\6\15\1\0\2\15"+
    "\1\0\1\15\12\0\10\15\1\145\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\6\15\1\0\2\15\1\0"+
    "\1\15\12\0\6\15\1\146\2\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\6\15\1\0\2\15\1\0"+
    "\1\15\12\0\3\15\1\147\5\15\7\0\1\15\1\0"+
    "\2\55\1\150\2\55\1\121\61\55\6\0\1\62\71\0"+
    "\1\63\70\0\2\124\1\0\1\65\30\0\1\65\32\0"+
    "\2\125\65\0\2\15\1\0\1\15\1\0\3\15\1\151"+
    "\2\15\1\0\2\15\1\0\1\15\12\0\11\15\7\0"+
    "\1\15\14\0\2\15\1\0\1\15\1\0\6\15\1\0"+
    "\2\15\1\0\1\15\12\0\3\15\1\152\5\15\7\0"+
    "\1\15\14\0\2\15\1\0\1\15\1\0\6\15\1\0"+
    "\2\15\1\0\1\15\12\0\4\15\1\153\4\15\7\0"+
    "\1\15\14\0\2\15\1\0\1\15\1\0\6\15\1\0"+
    "\2\15\1\0\1\15\12\0\10\15\1\154\7\0\1\15"+
    "\14\0\2\15\1\0\1\15\1\0\6\15\1\0\2\15"+
    "\1\0\1\15\12\0\7\15\1\155\1\15\7\0\1\15"+
    "\14\0\2\15\1\0\1\15\1\0\6\15\1\0\2\15"+
    "\1\0\1\15\12\0\2\15\1\156\6\15\7\0\1\15"+
    "\14\0\2\15\1\0\1\15\1\0\1\15\1\157\4\15"+
    "\1\0\2\15\1\0\1\15\12\0\11\15\7\0\1\15"+
    "\23\0\1\160\57\0\2\15\1\0\1\15\1\0\6\15"+
    "\1\0\2\15\1\0\1\15\12\0\2\15\1\161\6\15"+
    "\7\0\1\15\2\0\1\141\1\0\1\162\4\0\1\141"+
    "\71\0\2\15\1\0\1\15\1\0\6\15\1\0\2\15"+
    "\1\0\1\15\12\0\7\15\1\163\1\15\7\0\1\15"+
    "\14\0\2\15\1\0\1\15\1\0\6\15\1\0\2\15"+
    "\1\0\1\15\12\0\2\15\1\164\6\15\7\0\1\15"+
    "\14\0\2\15\1\0\1\15\1\0\6\15\1\0\1\15"+
    "\1\165\1\0\1\15\12\0\11\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\6\15\1\0\2\15\1\0"+
    "\1\15\12\0\2\15\1\166\6\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\2\15\1\167\3\15\1\0"+
    "\2\15\1\0\1\15\12\0\11\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\1\15\1\170\4\15\1\0"+
    "\2\15\1\0\1\15\12\0\11\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\6\15\1\0\2\15\1\0"+
    "\1\15\12\0\2\15\1\171\6\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\4\15\1\172\1\15\1\0"+
    "\2\15\1\0\1\15\12\0\11\15\7\0\1\15\14\0"+
    "\2\15\1\0\1\15\1\0\6\15\1\0\2\15\1\0"+
    "\1\15\12\0\1\173\10\15\7\0\1\15\24\0\1\174"+
    "\56\0\2\15\1\0\1\15\1\0\6\15\1\0\2\15"+
    "\1\0\1\15\12\0\1\175\10\15\7\0\1\15\2\0"+
    "\1\176\1\0\1\176\4\0\1\176\71\0\2\15\1\0"+
    "\1\15\1\0\6\15\1\0\2\15\1\0\1\15\12\0"+
    "\1\177\10\15\7\0\1\15\14\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\3\15"+
    "\1\200\5\15\7\0\1\15\14\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\1\201"+
    "\10\15\7\0\1\15\14\0\2\15\1\0\1\15\1\0"+
    "\3\15\1\202\2\15\1\0\2\15\1\0\1\15\12\0"+
    "\11\15\7\0\1\15\14\0\2\15\1\0\1\15\1\0"+
    "\3\15\1\203\2\15\1\0\2\15\1\0\1\15\12\0"+
    "\11\15\7\0\1\15\14\0\2\15\1\0\1\15\1\0"+
    "\4\15\1\204\1\15\1\0\2\15\1\0\1\15\12\0"+
    "\11\15\7\0\1\15\22\0\1\205\60\0\2\15\1\0"+
    "\1\15\1\0\4\15\1\206\1\15\1\0\2\15\1\0"+
    "\1\15\12\0\11\15\7\0\1\15\1\0\31\176\1\207"+
    "\35\176\13\0\2\15\1\0\1\15\1\0\4\15\1\210"+
    "\1\15\1\0\2\15\1\0\1\15\12\0\11\15\7\0"+
    "\1\15\14\0\2\15\1\0\1\15\1\0\6\15\1\0"+
    "\2\15\1\0\1\15\12\0\7\15\1\211\1\15\7\0"+
    "\1\15\14\0\2\15\1\0\1\15\1\0\6\15\1\0"+
    "\2\15\1\0\1\15\12\0\4\15\1\212\4\15\7\0"+
    "\1\15\24\0\1\213\43\0\31\176\1\214\35\176\13\0"+
    "\2\15\1\0\1\15\1\0\5\15\1\137\1\0\2\15"+
    "\1\0\1\15\12\0\11\15\7\0\1\15\14\0\2\15"+
    "\1\0\1\15\1\0\1\15\1\215\4\15\1\0\2\15"+
    "\1\0\1\15\12\0\11\15\7\0\1\15\14\0\2\15"+
    "\1\0\1\15\1\0\4\15\1\216\1\15\1\0\2\15"+
    "\1\0\1\15\12\0\11\15\7\0\1\15\25\0\1\217"+
    "\42\0\31\176\1\220\35\176\13\0\2\15\1\0\1\15"+
    "\1\0\6\15\1\0\2\15\1\0\1\15\12\0\1\221"+
    "\10\15\7\0\1\15\26\0\1\222\41\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5610];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\1\11\5\1\1\11\1\1\1\11"+
    "\11\1\10\11\5\1\1\11\4\1\3\11\1\1\1\11"+
    "\2\0\1\11\2\1\1\0\13\1\1\0\3\1\1\11"+
    "\6\1\6\11\1\0\14\1\1\0\2\1\1\0\1\11"+
    "\5\1\1\11\7\1\1\0\1\1\1\0\11\1\1\0"+
    "\1\1\1\0\6\1\1\0\1\1\1\0\3\1\2\0"+
    "\2\1\1\0\1\11\1\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  JsonnetLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        zzDoEOF();
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return BAD_CHARACTER;
            } 
            // fall through
          case 62: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 63: break;
          case 3: 
            { return SLASH;
            } 
            // fall through
          case 64: break;
          case 4: 
            { return LINE_COMMENT;
            } 
            // fall through
          case 65: break;
          case 5: 
            { return ASTERISK;
            } 
            // fall through
          case 66: break;
          case 6: 
            { return DOUBLE_QUOTED_STRING;
            } 
            // fall through
          case 67: break;
          case 7: 
            { return SINGLE_QUOTED_STRING;
            } 
            // fall through
          case 68: break;
          case 8: 
            { return NUMBER;
            } 
            // fall through
          case 69: break;
          case 9: 
            { return DOT;
            } 
            // fall through
          case 70: break;
          case 10: 
            { return IDENTIFIER;
            } 
            // fall through
          case 71: break;
          case 11: 
            { return PLUS;
            } 
            // fall through
          case 72: break;
          case 12: 
            { return MINUS;
            } 
            // fall through
          case 73: break;
          case 13: 
            { return BAR;
            } 
            // fall through
          case 74: break;
          case 14: 
            { return L_CURLY;
            } 
            // fall through
          case 75: break;
          case 15: 
            { return R_CURLY;
            } 
            // fall through
          case 76: break;
          case 16: 
            { return L_BRACKET;
            } 
            // fall through
          case 77: break;
          case 17: 
            { return R_BRACKET;
            } 
            // fall through
          case 78: break;
          case 18: 
            { return L_PAREN;
            } 
            // fall through
          case 79: break;
          case 19: 
            { return R_PAREN;
            } 
            // fall through
          case 80: break;
          case 20: 
            { return COMMA;
            } 
            // fall through
          case 81: break;
          case 21: 
            { return SEMICOLON;
            } 
            // fall through
          case 82: break;
          case 22: 
            { return EQUAL;
            } 
            // fall through
          case 83: break;
          case 23: 
            { return COLON;
            } 
            // fall through
          case 84: break;
          case 24: 
            { return PERCENT;
            } 
            // fall through
          case 85: break;
          case 25: 
            { return LESS_THAN;
            } 
            // fall through
          case 86: break;
          case 26: 
            { return GREATER_THAN;
            } 
            // fall through
          case 87: break;
          case 27: 
            { return EXCLAMATION;
            } 
            // fall through
          case 88: break;
          case 28: 
            { return AND;
            } 
            // fall through
          case 89: break;
          case 29: 
            { return CARAT;
            } 
            // fall through
          case 90: break;
          case 30: 
            { return TILDE;
            } 
            // fall through
          case 91: break;
          case 31: 
            { return DOLLAR;
            } 
            // fall through
          case 92: break;
          case 32: 
            { return BLOCK_COMMENT;
            } 
            // fall through
          case 93: break;
          case 33: 
            { return VERBATIM_DOUBLE_QUOTED_STRING;
            } 
            // fall through
          case 94: break;
          case 34: 
            { return VERBATIM_SINGLE_QUOTED_STRING;
            } 
            // fall through
          case 95: break;
          case 35: 
            { return IN;
            } 
            // fall through
          case 96: break;
          case 36: 
            { return IF;
            } 
            // fall through
          case 97: break;
          case 37: 
            { return DOUBLE_BAR;
            } 
            // fall through
          case 98: break;
          case 38: 
            { return DOUBLE_EQUAL;
            } 
            // fall through
          case 99: break;
          case 39: 
            { return COLON2;
            } 
            // fall through
          case 100: break;
          case 40: 
            { return LESS_EQUAL;
            } 
            // fall through
          case 101: break;
          case 41: 
            { return DOUBLE_LESS;
            } 
            // fall through
          case 102: break;
          case 42: 
            { return GREATER_EQUAL;
            } 
            // fall through
          case 103: break;
          case 43: 
            { return DOUBLE_GREATER;
            } 
            // fall through
          case 104: break;
          case 44: 
            { return NOT_EQUAL;
            } 
            // fall through
          case 105: break;
          case 45: 
            { return DOUBLE_AND;
            } 
            // fall through
          case 106: break;
          case 46: 
            { return FOR;
            } 
            // fall through
          case 107: break;
          case 47: 
            { return COLON3;
            } 
            // fall through
          case 108: break;
          case 48: 
            { return NULL;
            } 
            // fall through
          case 109: break;
          case 49: 
            { return TRUE;
            } 
            // fall through
          case 110: break;
          case 50: 
            { return THEN;
            } 
            // fall through
          case 111: break;
          case 51: 
            { return ELSE;
            } 
            // fall through
          case 112: break;
          case 52: 
            { return SELF;
            } 
            // fall through
          case 113: break;
          case 53: 
            { return FALSE;
            } 
            // fall through
          case 114: break;
          case 54: 
            { return ERROR;
            } 
            // fall through
          case 115: break;
          case 55: 
            { return LOCAL;
            } 
            // fall through
          case 116: break;
          case 56: 
            { return SUPER;
            } 
            // fall through
          case 117: break;
          case 57: 
            { return IMPORT;
            } 
            // fall through
          case 118: break;
          case 58: 
            { return ASSERT;
            } 
            // fall through
          case 119: break;
          case 59: 
            { return FUNCTION;
            } 
            // fall through
          case 120: break;
          case 60: 
            { return TRIPLE_BAR_QUOTED_STRING;
            } 
            // fall through
          case 121: break;
          case 61: 
            { return IMPORTSTR;
            } 
            // fall through
          case 122: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}