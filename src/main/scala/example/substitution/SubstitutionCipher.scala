package example.substitution

import example.Cipher

abstract class SubstitutionCipher extends Cipher {
  protected val scrambler: Scrambler

  private def runSubstitution(text: String, substitutor:( Char, Int) => Char): String = {
    text
      .toUpperCase()
      .zipWithIndex
      .foldLeft("")((res, charWithIndex) => res + substitutor(charWithIndex._1, charWithIndex._2))
  }

  override def encipher(plainText: String): String =
    runSubstitution(plainText, scrambler.scramble)

  override def decipher(enciphered: String): String =
    runSubstitution(enciphered, scrambler.unscramble)
      .toLowerCase()
}
