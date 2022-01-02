package example.substitution.monoaphabetic

import example.substitution.SubstitutionCipher

class CaesarCipher(shift: Int) extends SubstitutionCipher {
  lazy val substitution: String =
    (alphabets.takeRight(alphabets.length - shift) ++ alphabets.take(shift))
      .mkString("")
}
