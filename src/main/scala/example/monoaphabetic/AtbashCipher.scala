package example.monoaphabetic

class AtbashCipher() extends SubstitutionCipher {
  lazy val substitution: String = alphabets.reverse.mkString("")
}
