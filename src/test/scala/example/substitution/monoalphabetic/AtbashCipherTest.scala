package example.substitution.monoalphabetic

import example.substitution.monoaphabetic.AtbashCipher
import org.scalatest._

class AtbashCipherTest extends FlatSpec with Matchers {
  "Codebook" should "encipher with Atbash Cipher" in {
    val cipherText = new AtbashCipher().encipher("the quick brown fox jumps over the lazy dog")
    cipherText shouldBe "GSV JFRXP YILDM ULC QFNKH LEVI GSV OZAB WLT"
  }

  "Codebook" should "decipher Atbash Cipher" in {
    val plainText = new AtbashCipher().decipher("GSV JFRXP YILDM ULC QFNKH LEVI GSV OZAB WLT")
    plainText shouldBe "the quick brown fox jumps over the lazy dog"
  }
}
