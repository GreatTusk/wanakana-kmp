package dev.esnault.wanakana.utils

import dev.esnault.wanakana.helpers.EN_PUNC
import dev.esnault.wanakana.helpers.JA_PUNC
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class CharExtTest {

    @Test
    fun isHiragana() {
        assertTrue('な'.isHiragana())
        assertFalse('ナ'.isHiragana())
        assertFalse('n'.isHiragana())
        assertFalse('!'.isHiragana())
    }

    @Test
    fun isKatakana() {
        assertTrue('ナ'.isKatakana())
        assertFalse('は'.isKatakana())
        assertFalse('n'.isKatakana())
        assertFalse('!'.isKatakana())
    }

    @Test
    fun isKana() {
        assertTrue('は'.isKana())
        assertTrue('ナ'.isKana())
        assertFalse('n'.isKana())
        assertFalse('!'.isKana())
        assertFalse('-'.isKana())
        assertTrue('ー'.isKana())
    }

    @Test
    fun isLongDash() {
        assertTrue('ー'.isLongDash())
        assertFalse('-'.isLongDash())
        assertFalse('f'.isLongDash())
        assertFalse('ふ'.isLongDash())
    }

    @Test
    fun isSlashDot() {
        assertTrue('・'.isSlashDot())
        assertFalse('/'.isSlashDot())
    }

    @Test
    fun isUpperCase() {
        assertTrue('A'.isEnglishUpperCase())
        assertTrue('D'.isEnglishUpperCase())
        assertFalse('-'.isEnglishUpperCase())
        assertFalse('ー'.isEnglishUpperCase())
        assertFalse('a'.isEnglishUpperCase())
        assertFalse('d'.isEnglishUpperCase())
    }

    @Test
    fun isKanji() {
        assertTrue('腹'.isKanji())
        assertTrue('一'.isKanji()) // kanji for いち・1 - not a long hyphen
        assertFalse('ー'.isKanji()) // long hyphen
        assertFalse('は'.isKanji())
        assertFalse('ナ'.isKanji())
        assertFalse('n'.isKanji())
        assertFalse('!'.isKanji())
    }

    @Test
    fun isRomaji() {
        assertTrue('n'.isRomaji())
        assertTrue('!'.isRomaji())
        assertFalse('ナ'.isRomaji())
        assertFalse('は'.isRomaji())
        assertFalse('缶'.isRomaji())
    }

    @Test
    fun isEnglishPunctuation() {
        EN_PUNC.forEach { char -> assertTrue(char.isEnglishPunctuation()) }
        JA_PUNC.forEach { char -> assertFalse(char.isEnglishPunctuation()) }
        assertTrue(' '.isEnglishPunctuation())
        assertFalse('a'.isEnglishPunctuation())
        assertFalse('ふ'.isEnglishPunctuation())
        assertFalse('字'.isEnglishPunctuation())
    }

    @Test
    fun isJapanesePunctuation() {
        JA_PUNC.forEach { char -> assertTrue(char.isJapanesePunctuation()) }
        EN_PUNC.forEach { char -> assertFalse(char.isJapanesePunctuation()) }
        assertTrue('　'.isJapanesePunctuation())
        assertFalse('?'.isJapanesePunctuation())
        assertFalse('a'.isJapanesePunctuation())
        assertFalse('ふ'.isJapanesePunctuation())
        assertFalse('字'.isJapanesePunctuation())
    }
}
