package example.substitution.monoalphabetic

import example.substitution.monoaphabetic.CaesarCipher
import org.scalatest._

class CaesarCipherTest extends FlatSpec with Matchers {
  "Codebook" should "encipher with Caesar Cipher" in {
    val cipherText = new CaesarCipher(23).encipher("the quick brown fox jumps over the lazy dog")
    cipherText shouldBe "QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD"
  }

  "Codebook" should "decipher Caesar Cipher" in {
    val plainText = new CaesarCipher(23).decipher("QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD")
    plainText shouldBe "the quick brown fox jumps over the lazy dog"
  }
}
