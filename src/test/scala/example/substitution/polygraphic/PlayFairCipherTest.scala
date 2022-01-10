package example.substitution.polygraphic

import org.scalatest.{FlatSpec, Matchers}

class PlayFairCipherTest extends FlatSpec with Matchers {
  "Codebook" should "encode with PlayFair Cipher" in {
    val cipherText = new PlayFairCipher("CHARALES").encipher("charles")
    cipherText shouldBe "HARLCFBW"
  }
}
