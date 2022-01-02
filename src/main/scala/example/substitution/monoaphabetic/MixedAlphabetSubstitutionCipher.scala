package example.substitution.monoaphabetic

import example.substitution.{MixedAlphabetScrambler, SubstitutionCipher}

class MixedAlphabetSubstitutionCipher(key: String) extends SubstitutionCipher {
  override val scrambler = new MixedAlphabetScrambler(key)
}
