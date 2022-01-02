package example.substitution

import example.Cipher

abstract class SubstitutionCipher extends Cipher {
  protected val scrambler: Scrambler

  def runSubstitution(text: String, substitutor: Char => Char): String = {
    text
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + substitutor(char.charAt(0)))
  }

  override def encipher(plainText: String): String =
    runSubstitution(plainText, scrambler.scramble)

  override def decipher(enciphered: String): String =
    runSubstitution(enciphered, scrambler.unscramble)
      .toLowerCase()
}
