package example.substitution.monoaphabetic

import example.substitution.{AtbashScrambler, Scrambler, SubstitutionCipher}

class AtbashCipher() extends SubstitutionCipher {
  override val scrambler: Scrambler = AtbashScrambler
}
