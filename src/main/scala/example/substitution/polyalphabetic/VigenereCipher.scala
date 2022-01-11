package example.substitution.polyalphabetic

import example.Alphabets.alphabets
import example.substitution.{MonographicScrambler, MonographicSubstitutionCipher}

class VigenereCipher(key: String) extends MonographicSubstitutionCipher {
  override protected val scrambler: MonographicScrambler = new VigenereScrambler(key)
}

class VigenereScrambler(key: String) extends MonographicScrambler {
  private val upperCasedKey = key.toUpperCase()
  private val shifts = upperCasedKey.map(alphabets.indexOf)

  override def scramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      val currentPosition = alphabets.indexOf(char)
      val shift = shifts(index % shifts.length)
      val nextPosition = (currentPosition + shift) % alphabets.length
      alphabets(nextPosition)
    } else char
  }

  override def unscramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      val currentPosition = alphabets.indexOf(char)
      val shift = shifts(index % shifts.length)
      val nextPosition = (currentPosition + (alphabets.length - shift)) % alphabets.length
      alphabets(nextPosition)
    } else char
  }
}
