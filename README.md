# WanaKana KMP

Kotlin Multiplatform (KMP) utility library for detecting and transliterating Hiragana, Katakana, and Romaji.

Ported from [esnaultdev/wanakana-kt][wanakana-kt-repo], which in turn was ported from [WaniKani/WanaKana][wanakana-repo] v4.0.2.
This library is written in Kotlin and supports multiple platforms through KMP, but does not include Android-specific bindings.

## Demo
Visit the [website][wanakana-website] to see WanaKana in action (JS version).

## Installation
```kotlin
implementation("io.github.greattusk:wanakana-kmp:1.0.0")
```
Ensure your buildscript repositories include `mavenCentral()`.

## Documentation
- [Index][docs-index]
  - [Wanakana (core functions)][docs-wanakana]

## Quick Reference

### Text conversion
```kotlin
Wanakana.toKana("ONAJI buttsuuji")
// => "オナジ ぶっつうじ"
Wanakana.toKana("座禅‘zazen’スタイル")
// => "座禅「ざぜん」スタイル"
Wanakana.toHiragana("toukyou, オオサカ")
// => "とうきょう、　おおさか"
Wanakana.toKatakana("toukyou, おおさか")
// => "トウキョウ、　オオサカ"
Wanakana.toRomaji("ひらがな　カタカナ")
// => "hiragana katakana"
```

### Text checking utilities
```kotlin
Wanakana.isJapanese("泣き虫。！〜２￥ｚｅｎｋａｋｕ")
// => true
Wanakana.isHiragana("すげー")
// => true
Wanakana.isKatakana("ゲーム")
// => true
Wanakana.isKanji("切腹")
// => true
Wanakana.isRomaji("Tōkyō and Ōsaka")
// => true
```

### Extra utilities
```kotlin
Wanakana.stripOkurigana("お祝い")
// => "お祝"
Wanakana.tokenize("hello 田中さん")
// => ["hello", " ", "田中", "さん"]
```

## Differences with Wanakana KT
|               | Wanakana KT  | WanaKana KMP |
| ------------- | ------------- | ------------- |
| Multiplatform support | JVM and Android only | Kotlin Multiplatform (Common, JVM, JS, Native, iOS) |
| Android bindings | Included | Not included |

## Credits
Original JavaScript library sponsored by [Tofugu][tofugu] & [WaniKani][wanikani].
Kotlin port by [esnaultdev][wanakana-kt-repo].

[wanakana-repo]: https://github.com/WaniKani/WanaKana
[wanakana-website]: http://www.wanakana.com
[wanakana-kt-repo]: https://github.com/esnaultdev/wanakana-kt
[wanikani]: http://www.wanikani.com
[tofugu]: http://www.tofugu.com
[docs-index]: https://esnaultdev.github.io/wanakana-kt/wanakana-core/index.html
[docs-wanakana]: https://esnaultdev.github.io/wanakana-kt/wanakana-core/wanakana-core/dev.esnault.wanakana.core/-wanakana/index.html

