package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.SubstitutionCipher

class MixedAlphabetSubstitutionCipher(key: String) extends SubstitutionCipher {
  lazy val substitution: String =
    alphabets.fold(
      key.toUpperCase()
    )((res, char) => if (!res.contains(char))
      res + char
    else res
    )
}
