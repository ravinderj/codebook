package example

trait Cipher {
  val alphabets: Array[String] = Array("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
    "U", "V", "W", "X", "Y", "Z")
  def encipher(plainText: String): String
  def decipher(cipherText: String): String
}
