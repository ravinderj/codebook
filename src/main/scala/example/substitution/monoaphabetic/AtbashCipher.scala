package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.SubstitutionCipher

class AtbashCipher() extends SubstitutionCipher {
  lazy val substitution: String = alphabets.reverse.mkString("")
}
