package example.substitution.monoaphabetic

import example.substitution.{CaesarScrambler, SubstitutionCipher}

class CaesarCipher(shift: Int) extends SubstitutionCipher {
  val scrambler = new CaesarScrambler(shift)
}
