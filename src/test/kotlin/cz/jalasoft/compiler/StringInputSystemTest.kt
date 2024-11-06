package cz.jalasoft.compiler

import cz.jalasoft.compiler.input.StringInputSystem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringInputSystemTest {

    @Test
    fun test1() {

        val input = StringInputSystem("token1 token2")

        val buffer = buildString {
            var char = input.nextChar()
            while(char != null) {
                append(char)
                char = input.nextChar()
            }
        }

        Assertions.assertEquals("token1 token2", buffer)
    }
}