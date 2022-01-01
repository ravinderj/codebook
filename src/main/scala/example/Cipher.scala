package example

trait Cipher {
  def encipher(plainText: String, key: String): String
  def decipher(cipherText: String, key: String): String
}
