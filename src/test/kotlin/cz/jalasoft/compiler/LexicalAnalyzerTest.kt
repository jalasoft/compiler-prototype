package cz.jalasoft.compiler

import cz.jalasoft.compiler.input.StringInputSystem
import cz.jalasoft.compiler.lexan.*
import org.junit.jupiter.api.Test

class LexicalAnalyzerTest {

    fun LexicalAnalyzer.readAll() : List<LexicalSymbol> {
        val collection = mutableListOf<LexicalSymbol>()

        var symbol = next()
        while (symbol != EndOfInput) {
            collection.add(symbol)
            symbol = next()
        }

        return collection
    }

    @Test
    fun test1() {
        val analyzer = LexicalAnalyzer(StringInputSystem("identifikator 234 TRUE"))
        analyzer.init()
        val actual = analyzer.readAll()

        assertSymbols(actual, Identifier("identifikator"),  IntNumberLiteral(234), BooleanLiteral(true))
    }

    @Test
    fun test2() {
        val analyzer = LexicalAnalyzer(StringInputSystem("  identifikator    45.4   FALSE * +     == "))
        analyzer.init()
        val actual = analyzer.readAll()

        assertSymbols(actual, Identifier("identifikator"),  DecimalNumberLiteral(45.4f), BooleanLiteral(false), Multiply, Plus, Equal(false))
    }

    @Test
    fun test3() {
        val code = """
            a = 'hello'
            b = 2
            c = 4.55
            d = FALSE
        """.trimIndent()
        val analyzer = LexicalAnalyzer(StringInputSystem(code))
        analyzer.init()
        val actual = analyzer.readAll()

        assertSymbols(actual,
            Identifier("a"), Assign, StringLiteral("hello"),
            Identifier("b"), Assign, IntNumberLiteral(2),
            Identifier("c"), Assign, DecimalNumberLiteral(4.55f),
            Identifier("d"), Assign, BooleanLiteral(false))
    }

    @Test
    fun test4() {
        val code = """
            b = (2 > b and c < 88.9)
        """.trimIndent()
        val analyzer = LexicalAnalyzer(StringInputSystem(code))
        analyzer.init()
        val actual = analyzer.readAll()

        assertSymbols(actual,
            Identifier("b"), Assign, LeftBracket,
            IntNumberLiteral(2), GreaterThan(), Identifier("b"),
            LogicalAnd, Identifier("c"), LessThan(), DecimalNumberLiteral(88.9f),
            RightBracket)
    }

    @Test
    fun test5() {
        val code = """
            a = 3
            if(a > 2) { 'ahoj' }
        """.trimIndent()

        val analyzer = LexicalAnalyzer(StringInputSystem(code))
        analyzer.init()
        val actual = analyzer.readAll()

        assertSymbols(actual, Identifier("a"), Assign, IntNumberLiteral(3), CondIf, LeftBracket,
            Identifier("a"), GreaterThan(), IntNumberLiteral(2), RightBracket, LeftCurlyBracket,
            StringLiteral("ahoj"), RightCurlyBracket
        )
    }

    private fun assertSymbols(actual: List<LexicalSymbol>, vararg expected: LexicalSymbol) {
        if (actual.size != expected.size) {
            throw AssertionError("Size of expected symbols: ${expected.size}. Actual: ${actual.size}")
        }

        for ((i, actualSymbol) in actual.withIndex()) {
            val expectedSymbol = expected[i]

            if (actualSymbol != expectedSymbol) {
                throw AssertionError("Expected symbol: $expectedSymbol. Actual: $actualSymbol")
            }
        }
    }
}