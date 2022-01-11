package example.substitution.monoalphabetic

import example.substitution.monoaphabetic.{MixedAlphabetCipher => MACipher}
import org.scalatest._

class MixedAlphabetCipherTest extends FlatSpec with Matchers {
  "Codebook" should "encipher with Mixed Alphabet Substitution Cipher" in {
    val cipherText = new MACipher("zebras").encipher("flee at once. we are discovered!")
    cipherText shouldBe "SIAA ZQ LKBA. VA ZOA RFPBLUAOAR!"
  }

  "Codebook" should "decipher Mixed Alphabet Substitution Cipher" in {
    val plainText = new MACipher("zebras").decipher("SIAA ZQ LKBA. VA ZOA RFPBLUAOAR!")
    plainText shouldBe "flee at once. we are discovered!"
  }
}
