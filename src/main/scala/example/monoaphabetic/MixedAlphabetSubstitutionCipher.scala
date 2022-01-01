package example.monoaphabetic

class MixedAlphabetSubstitutionCipher(key: String) extends SubstitutionCipher {
  lazy val substitution: String =
    alphabets.fold(
      key.toUpperCase()
    )((res, char) => if (!res.contains(char))
      res + char
    else res
    )
}
