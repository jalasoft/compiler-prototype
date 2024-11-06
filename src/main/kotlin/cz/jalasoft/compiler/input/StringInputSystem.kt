package cz.jalasoft.compiler.input

class StringInputSystem(
    private val input : String
) : InputSystem {

    private var position: Int = 0

    override fun nextChar(): Char? {
        return if (position >= input.length) null else input[position++]
    }
}