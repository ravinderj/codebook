package example.substitution

import example.Cipher

abstract class SubstitutionCipher extends Cipher {
  protected val scrambler: Scrambler

  def runSubstitution(text: String, substitutor:( Char, Int) => Char): String = {
    text
      .toUpperCase()
      .split("")
      .zipWithIndex
      .foldLeft("")((res, char) => res + substitutor(char._1.charAt(0), char._2))
  }

  override def encipher(plainText: String): String =
    runSubstitution(plainText, scrambler.scramble)

  override def decipher(enciphered: String): String =
    runSubstitution(enciphered, scrambler.unscramble)
      .toLowerCase()
}
