package example.substitution.polyalphabetic

import org.scalatest._

class VigenereCipherTest extends FlatSpec with Matchers {
  "Codebook" should "encode with Vigenere Cipher" in {
    val cipherText = new VigenereCipher("LEMON").encipher("attackatdawn")
    cipherText shouldBe "LXFOPVEFRNHR"
  }

  "Codebook" should "decode Vigenere Cipher" in {
    val plainText = new VigenereCipher("LEMON").decipher("LXFOPVEFRNHR")
    plainText shouldBe "attackatdawn"
  }
}
