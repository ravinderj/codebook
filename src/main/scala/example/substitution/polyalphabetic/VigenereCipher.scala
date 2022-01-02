package example.substitution.polyalphabetic

import example.Cipher
import example.Alphabets.alphabets

class VigenereCipher(key: String) extends Cipher {
  override def encipher(plainText: String): String = {
    plainText.toUpperCase().zipWithIndex.foldLeft("")((acc, c) => {
      val (ch, i) = c
      val curPosition = alphabets.indexOf(ch.toString)
      val shiftByChar = key(i % key.length).toString
      val shift = alphabets.indexOf(shiftByChar)
      acc + alphabets((curPosition + shift) % alphabets.length)
    })
  }

  override def decipher(enciphered: String): String = ???
}
