package example.substitution

import example.Cipher

import scala.annotation.tailrec

abstract class MonographicSubstitutionCipher extends Cipher {
  protected val scrambler: MonographicScrambler

  private def runSubstitution(text: String, substitutor: (Char, Int) => Char): String = {
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

abstract class PolygraphicSubstitutionCipher extends Cipher {
  protected val scrambler: PolygraphicScrambler

  @tailrec
  private def runSubstitution(text: String, scrambler: ((Char, Char)) => String, res: String = ""): String = {
    if (text.isEmpty) return res
    val charsRead = text.take(2).toUpperCase match {
      case a if a.length == 1 => (1, (a(0), 'X'))
      case a if a(0) == a(1) => (1, (a(0), 'X'))
      case a => (2, (a(0), a(1)))
    }
    runSubstitution(text.substring(charsRead._1),
      scrambler,
      res + scrambler(charsRead._2))
  }

  override def encipher(plainText: String): String = {
    runSubstitution(plainText.replaceAllLiterally(" ", ""), scrambler.scramble)
  }

  override def decipher(enciphered: String): String = {
    runSubstitution(enciphered.replaceAllLiterally(" ", ""), scrambler.unscramble)
      .toLowerCase()
  }
}
