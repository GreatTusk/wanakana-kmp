package dev.esnault.wanakana

import dev.esnault.wanakana.utils.ConversionToken
import dev.esnault.wanakana.utils.MappingTree
import dev.esnault.wanakana.utils.applyMapping
import dev.esnault.wanakana.utils.hiraganaToKatakana
import dev.esnault.wanakana.utils.isEnglishUpperCase
import dev.esnault.wanakana.utils.kanaImeMode
import dev.esnault.wanakana.utils.romajiToKanaMap
import dev.esnault.wanakana.utils.safeLowerCase
import dev.esnault.wanakana.utils.useObsoleteKana

/**
 * Converts Romaji to Kana.
 * Lowercase text will result in Hiragana and uppercase text will result in Katakana.
 *
 * @param input the text to convert to Kana.
 * @return the converted text.
 *
 * See [Romaji](https://en.wikipedia.org/wiki/Romaji).
 * See [Kana](https://en.wikipedia.org/wiki/Kana).
 * See [Hiragana](https://en.wikipedia.org/wiki/Hiragana).
 * See [Katakana](https://en.wikipedia.org/wiki/Katakana).
 *
 * For example:
 * - `toKana("onaji BUTTSUUJI")` => `"おなじ ブッツウジ"`
 * - `toKana("ONAJI buttsuuji")` => `"オナジ ぶっつうじ"`
 * - `toKana("座禅‘zazen’スタイル")` => `"座禅「ざぜん」スタイル"`
 * - `toKana("batsuge-mu")` => `"ばつげーむ"`
 * - `toKana("!?.:/,~-‘’“”[](){}")` => `"！？。：・、〜ー「」『』［］（）｛｝"`
 */
fun toKana(
    input: String,
    imeMode: IMEMode = IMEMode.DISABLED,
    useObsoleteKana: Boolean = false
): String {
    // TODO introduce useObsoleteKana to other methods
    // TODO introduce imeMode to other methods
    val map = createRomajiToKanaMap(imeMode, useObsoleteKana)

    val enforceHiragana = imeMode == IMEMode.TO_HIRAGANA
    val enforceKatakanaMode = imeMode == IMEMode.TO_KATAKANA

    // throw away the substring index information and just concatenate all the kana
    return splitIntoConvertedKana(input, map, imeMode)
        .joinToString(separator = "") { token ->
            val kana = token.value
            if (kana == null) {
                // haven't converted the end of the string, since we are in IME mode
                return@joinToString input.slice(token.range)
            }

            val enforceKatakana = enforceKatakanaMode
                    || input.slice(token.range).all { it.isEnglishUpperCase() }
            if (enforceHiragana || !enforceKatakana) kana else hiraganaToKatakana(kana)
        }
}

private fun splitIntoConvertedKana(
    input: String,
    map: MappingTree,
    imeMode: IMEMode
): List<ConversionToken> {
    return applyMapping(input.safeLowerCase(), map, imeMode == IMEMode.DISABLED)
}

private fun createRomajiToKanaMap(imeMode: IMEMode, useObsoleteKana: Boolean): MappingTree {
    var map = romajiToKanaMap
    if (imeMode != IMEMode.DISABLED) {
        map = kanaImeMode(map)
    }
    if (useObsoleteKana) {
        map = useObsoleteKana(map)
    }
    // TODO Introduce custom mappings
    return map
}
