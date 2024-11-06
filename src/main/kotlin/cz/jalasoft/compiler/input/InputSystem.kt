package cz.jalasoft.compiler.input

/**
 * Vstupni modul ma na starosti cteni textu ze zdroje - soubor, prikazova radka atd. Dalsi
 * zodpovednosti muze byt spojovani souboru pomoci direktiv. Zastava tedy roli preprocesoru.
 *
 * Obecne z nekolika fyzickych zdroju (zejmena souboru) tvori jeden logicky zdroj zdrojoveho
 * kodu a ten poskytuje znak po znaku lexikalnimu analyzatoru dokud nedojde na konec textu.
 * Za vstupnim systemem se muze skryvat napr. bufferovani vstupu protoze nemusi byt zrovna efektivni
 * cist ze souboru znak po znaku.
 *
 * Dulezita je indikace konce vstupu.
 */
interface InputSystem {

    fun nextChar() : Char?
}