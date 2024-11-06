package cz.jalasoft.compiler.lexan

sealed interface LexicalSymbol
data class Identifier(val value: String) : LexicalSymbol
data class StringLiteral(val value: String) : LexicalSymbol
data class IntNumberLiteral(val value: Int) : LexicalSymbol
data class DecimalNumberLiteral(val value: Float) : LexicalSymbol
data class BooleanLiteral(val value: Boolean) : LexicalSymbol
data class LessThan(val equal: Boolean = false) : LexicalSymbol
data class GreaterThan(val equal: Boolean = false) : LexicalSymbol
data class Equal(val not: Boolean) : LexicalSymbol
object Plus : LexicalSymbol
object Minus : LexicalSymbol
object Multiply : LexicalSymbol
object Divide : LexicalSymbol
object Assign : LexicalSymbol
object LogicalAnd : LexicalSymbol
object LogicalOr : LexicalSymbol
object LeftBracket : LexicalSymbol
object RightBracket : LexicalSymbol
object CondIf: LexicalSymbol
object LeftCurlyBracket : LexicalSymbol
object RightCurlyBracket : LexicalSymbol
data class Error(val input: String) : LexicalSymbol
data object EndOfInput : LexicalSymbol