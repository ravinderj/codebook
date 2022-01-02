package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.{CaesarScrambler, SubstitutionCipher}

class CaesarCipher(shift: Int) extends SubstitutionCipher {
  lazy val substitution: String =
    (alphabets.takeRight(alphabets.length - shift) ++ alphabets.take(shift))
      .mkString("")
  val scrambler = new CaesarScrambler(shift)

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
      .fold("")((res, char) => res + scrambler.unscramble(char.charAt(0)))
  }
}
