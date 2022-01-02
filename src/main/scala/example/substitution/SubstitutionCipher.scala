package example.substitution

import example.Cipher

abstract class SubstitutionCipher extends Cipher {
  val substitution: String
  val encodingTranslation: Seq[(String, String)] = alphabets
    .indices
    .map(index => (alphabets(index), substitution(index).toString))

  val decodingTranslation: Seq[(String, String)] = alphabets
    .indices
    .map(index => (substitution(index).toString, alphabets(index)))

  def runSubstitution(text: String, substitutionMapping: Map[String, String]): String = {
    text
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + substitutionMapping.getOrElse(char, char))
  }

  override def encipher(plainText: String): String =
    runSubstitution(plainText, Map[String, String](encodingTranslation: _*))

  override def decipher(enciphered: String): String =
    runSubstitution(enciphered, Map[String, String](decodingTranslation: _*))
      .toLowerCase()
}
