package example.substitution.polyalphabetic

import example.Alphabets.alphabets
import example.substitution.{Scrambler, SubstitutionCipher}

class VigenereCipher(key: String) extends SubstitutionCipher {
  override protected val scrambler: Scrambler = new VigenereScrambler(key)
}

class VigenereScrambler(key: String) extends Scrambler {
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
