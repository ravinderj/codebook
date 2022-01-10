package example.substitution.polygraphic

import org.scalatest.{FlatSpec, Matchers}

class PlayFairCipherTest extends FlatSpec with Matchers {
  "Codebook" should "encode with PlayFair Cipher" in {
    val cipherText = new PlayFairCipher("CHARALES").encipher("charles")
    cipherText shouldBe "HARLCFBW"
  }

  "Codebook" should "encode with words containing J" in {
    val cipherText = new PlayFairCipher("JACK").encipher("jocker")
    cipherText shouldBe "KLKBMW"
  }

  "Codebook" should "decode with PlayFair Cipher" in {
    val plainText = new PlayFairCipher("CHARALES").decipher("HARLCFBW")
    plainText shouldBe "charlesx"
  }

  "Codebook" should "decode some other word" in {
    val plainText = new PlayFairCipher("JACK").decipher("ICMF")
    plainText shouldBe "bane"
  }
}
