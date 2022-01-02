package example

trait Cipher {
  def encipher(plainText: String): String
  def decipher(cipherText: String): String
}
