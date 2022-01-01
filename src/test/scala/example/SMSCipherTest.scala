package example

import org.scalatest._

class SMSCipherTest extends FlatSpec with Matchers {
  "Codebook" should "encipher with Simple Monoalphabetic Substitution Cipher" in {
    val cipherText = SMSCipher.encipher("flee at once. we are discovered!", "zebras")
    cipherText shouldBe "SIAA ZQ LKBA. VA ZOA RFPBLUAOAR!"
  }

  "Codebook" should "decipher with Simple Monoalphabetic Substitution Cipher" in {
    val plainText = SMSCipher.decipher("SIAA ZQ LKBA. VA ZOA RFPBLUAOAR!", "zebras")
    plainText shouldBe "flee at once. we are discovered!"
  }
}
