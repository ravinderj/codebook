package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.{AtbashScrambler, SubstitutionCipher}

class AtbashCipher() extends SubstitutionCipher {
  lazy val substitution: String = alphabets.reverse.mkString("")

  override def encipher(plainText: String): String = {
    plainText
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + AtbashScrambler.scramble(char.charAt(0)))
  }

  override def decipher(enciphered: String): String = {
    enciphered
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + AtbashScrambler.unscramble(char.charAt(0)))
  }
}
