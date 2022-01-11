package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.{MonographicScrambler, MonographicSubstitutionCipher}

class MixedAlphabetCipher(key: String) extends MonographicSubstitutionCipher {
  override val scrambler = new MixedAlphabetScrambler(key)
}

class MixedAlphabetScrambler(key: String) extends MonographicScrambler {
  private val upperCasedKey = key.toUpperCase()
  private val substitute = upperCasedKey + alphabets
    .filterNot(upperCasedKey.contains(_))
    .mkString("")

  override def scramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      substitute.charAt(alphabets.indexOf(char))
    } else char
  }

  override def unscramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      alphabets(substitute.indexOf(char))
    } else char
  }
}
