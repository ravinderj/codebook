package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.{CaesarScrambler, MixedAlphabetScrambler, SubstitutionCipher}

class MixedAlphabetSubstitutionCipher(key: String) extends SubstitutionCipher {
  lazy val substitution: String =
    alphabets.fold(
      key.toUpperCase()
    )((res, char) => if (!res.contains(char))
      res + char
    else res
    )

  private val scrambler = new MixedAlphabetScrambler(key)

  override def encipher(plainText: String): String = {
    plainText
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + scrambler.scramble(char.charAt(0)))
  }

  override def decipher(enciphered: String): String = {
    enciphered
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + scrambler.unscramble(char.charAt(0))).toLowerCase()
  }
}
