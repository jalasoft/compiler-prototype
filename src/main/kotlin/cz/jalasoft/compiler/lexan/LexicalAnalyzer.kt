package cz.jalasoft.compiler.lexan

import cz.jalasoft.compiler.input.InputSystem

/**
 * Lexikalni symbol dostava na vstupu vstupni znaky. Vnitrne pracuje s tzv. vstupnimi
 * symboly. Jednomu vstupnimu symbolu muze odpovidat vice vstupnich znaku, napr. vstupnimu
 * symbolu NUMBER odpovidaji vstupni znaky 0-9.
 * Lexikalni analyzator umi prijimat jazyk generovany regularni bezkontextovou gramatikou.
 * Takovy jazyk muzeme vyjadrit take pomoci regularniho vyrazu nebo BNF.
 * Existuje 2 zakladni realizace - pomoci stavu a prechodove funkce nebo pomoci vnerenych
 * cyklu a podminek jejich vnorene flow odpovida prechodum stavoveho automatu. Tento druhy
 * zpusob realizace ma tu vyhodu ze promo do toku prechodu lzesnadno pridava semanticke akce,
 * napr. kontrola ze identifikator neni prilis dlouhy nebo uprava (vynasobeni 10) ciselne
 * hodnoty po precteni cislice ve vetvi automatu ktera odpovida ciselnemu literalu.
 *
 * Pro reprezentaci vsupnich symbolu je vyhodne pouzit Char, protoze jeho hodnota muze reprezentovat
 * primo vstupni znak, napr. '<', '=', '{', a v pripade vstupnho symbolu kteremu odpovid nekolik
 * vstupnich znaku muzeme pouzit specialni pimena, napr. 'd' pro cislici, 'c' pro znak. Ale take
 * napr. 'e' pro konec vstupu. Jadrem lexikalni analyzy je switch nad temito symboly, kde v
 * jednotlivych vetvych se pokracuje se cteni a testovanim symbolu tak jak je tomu v odpovidajicimu
 * stavovemu automatu.
 * Dulezite je zajistit ze automat cte dalsi a dalsi symboly tak dlouho dokud se muze posouvat ve
 * automatu dopredu. Jakmile narazi na vstupni symbol kteremu nerozumi, konci se ctenim a vraci
 * nalezeny lexikalni symbol (anebo chybu nebi konec vstupu).
 *
 * Lexikalni analyzator ma za ukol preskakovani komentaru
 *
 * <start> ::= <identifier> | <number> | <string> | < | <= | > | >= | + | - | * | / | ( | ) | { | }
 * <identifier> ::= <character><identifier_cont>
 * <identifier_cont> ::= <end> | <character><identifier_cont> | <digit><identifier_cont>
 * <number> ::= <digit><number_cont>
 * <number_cont> ::= <end> | <digit><number_cont> | <float>
 * <float> ::= .<float_cont>
 * <float_cont> ::= <digit><float_cont> | <end>
 * <string> ::= '<string_cont>'
 * <string_cont> ::= <char><string_cont> | <end>
 */

class LexicalAnalyzer(
    private val input: InputSystem
) {

    companion object {
        private val SPACE_DELIMITER_CHARS = charArrayOf(' ', '\t', '\r')

        private const val COMMENT_CHAR = '!'

        private val keyValueTable = mapOf(
            "TRUE" to BooleanLiteral(true),
            "FALSE" to BooleanLiteral(false),
            "and" to LogicalAnd,
            "or" to LogicalOr,
            "if" to CondIf
        )
    }

    private var currentChar : Char? = null
    private var currentSymbol: Char = 'e'

    private val textBuffer = StringBuilder()
    private var intNumber: Int = 0
    private var decimalNumber: Float = 0F
    private var decimalPower: Int = 1

    fun init() {
        readChar()
    }

    fun next() : LexicalSymbol {
        textBuffer.clear()
        intNumber = 0
        decimalNumber = 0F
        decimalPower = 1

        while(currentSymbol == ' ' || currentSymbol == '\n') {
            readChar()
        }

        when(currentSymbol) {
            'c' -> {
                textBuffer.clear()
                textBuffer.append(currentChar)
                readChar()
                while(currentSymbol == 'c' || currentSymbol == 'd') {
                    textBuffer.append(currentChar)
                    readChar()
                }
                return keyValueTable[textBuffer.toString()] ?: Identifier(textBuffer.toString())
            }
            'd' -> {
                intNumber = currentChar!!.digitToInt()
                readChar()
                while(currentSymbol == 'd') {
                    intNumber = 10 * intNumber + currentChar!!.digitToInt()
                    readChar()
                }

                if (currentSymbol == '.') {
                    readChar()
                    while(currentSymbol == 'd') {
                        decimalNumber += currentChar!!.digitToInt().toFloat() / 10.pow(decimalPower).toFloat()
                        decimalPower++
                        readChar()
                    }
                    return DecimalNumberLiteral(intNumber + decimalNumber)
                }
                return IntNumberLiteral(intNumber)
            }
            '\'' -> {
                textBuffer.clear()
                readChar()
                while(currentSymbol != '\'') {
                    textBuffer.append(currentChar)
                    readChar()
                }
                readChar()
                return StringLiteral(textBuffer.toString())
            }
            '<' -> {
                readChar()
                when (currentChar) {
                    '=' -> {
                        readChar()
                        return LessThan(equal = true)
                    }

                    '>' -> {
                        readChar()
                        return Equal(not = true)
                    }
                }
                return LessThan(equal = false)
            }
            '>' -> {
                readChar()
                if (currentChar == '=') {
                    readChar()
                    return GreaterThan(equal = true)
                }
                return GreaterThan(equal = false)
            }
            '+' -> {
                readChar()
                return Plus
            }
            '-' -> {
                readChar()
                return Minus
            }
            '*' -> {
                readChar()
                return Multiply
            }
            '/' -> {
                readChar()
                return Divide
            }
            '=' -> {
                readChar()
                if (currentChar == '=') {
                    readChar()
                    return Equal(not = false)
                }
                return Assign
            }
            '(' -> {
                readChar()
                return LeftBracket
            }
            ')' -> {
                readChar()
                return RightBracket
            }
            '!' -> {
                readChar()
                if (currentSymbol != '!') {
                    return Error("Expected $COMMENT_CHAR")
                }
                readChar()
                while(currentSymbol != '\n') {
                    readChar()
                }
            }
            'e' -> {
                return EndOfInput
            }
            else -> {
                return Error("Unexpected character: $currentChar")
            }
        }

        throw IllegalStateException()
    }

    private fun readChar() {
        val char = input.nextChar()
        currentChar = char
        currentSymbol = inputSymbol(char)
    }

    private fun inputSymbol(char: Char?) : Char {
        if (char == null) {
            return 'e'
        }
        return when {
            char.isLetter() -> 'c'
            char.isDigit() -> 'd'
            char in SPACE_DELIMITER_CHARS -> ' '
            else -> char
        }
    }

    private fun Int.pow(x: Int) : Int {
        var result = 1

        repeat(x) {
            result *= this
        }
        return result
    }
}



