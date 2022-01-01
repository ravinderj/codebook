package example.monoaphabetic

import example.Cipher

class CaesarCipher(shift: Int) extends Cipher {
  lazy val substitution: String =
    (alphabets.takeRight(alphabets.length - shift) ++ alphabets.take(shift))
      .mkString("")

  override def encipher(plainText: String): String = {
    val translation = alphabets
      .indices
      .map(index => (alphabets(index), substitution(index).toString))
    runSubstitution(plainText, Map[String, String](translation: _*))
  }

  private def runSubstitution(text: String, substitutionMapping: Map[String, String]) = {
    text
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + substitutionMapping.getOrElse(char, char))
  }

  override def decipher(enciphered: String): String = {
    val translation = alphabets
      .indices
      .map(index => (substitution(index).toString, alphabets(index)))
    runSubstitution(enciphered, Map[String, String](translation: _*))
      .toLowerCase()
  }
}
