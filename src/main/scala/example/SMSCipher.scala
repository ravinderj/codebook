package example

object SMSCipher extends Cipher {
  val alphabets: Array[String] = Array("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
    "U", "V", "W", "X", "Y", "Z")

  override def encipher(plainText: String, key: String): String = {
    val substitution = createSubstitution(alphabets, key)
    val translation = alphabets
      .indices
      .map(index => (alphabets(index), substitution(index).toString))
    runSubstitution(plainText, Map[String, String](translation: _*))
  }

  private def createSubstitution(alphabets: Array[String], key: String) = {
    alphabets.fold(
      key.toUpperCase()
    )((res, char) => if (!res.contains(char))
      res + char
    else res
    )
  }

  private def runSubstitution(text: String, substitutionMapping: Map[String, String]) = {
    text
      .toUpperCase()
      .split("")
      .fold("")((res, char) => res + substitutionMapping.getOrElse(char, char))
  }

  override def decipher(enciphered: String, key: String): String = {
    val substitution = createSubstitution(alphabets, key)
    val translation = alphabets
      .indices
      .map(index => (substitution(index).toString, alphabets(index)))
    runSubstitution(enciphered, Map[String, String](translation: _*))
      .toLowerCase()
  }
}
