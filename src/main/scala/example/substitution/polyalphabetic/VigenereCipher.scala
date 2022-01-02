package example.substitution.polyalphabetic

import example.substitution.{Scrambler, SubstitutionCipher, VigenereScrambler}

class VigenereCipher(key: String) extends SubstitutionCipher {
  override protected val scrambler: Scrambler = new VigenereScrambler(key)
}
