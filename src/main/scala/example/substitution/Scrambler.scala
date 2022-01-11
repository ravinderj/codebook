package example.substitution

trait MonographicScrambler {
  def scramble(char: Char, index: Int): Char

  def unscramble(char: Char, index: Int): Char
}

trait PolygraphicScrambler {
  def scramble(pair: (Char, Char)): String

  def unscramble(pair: (Char, Char)): String
}
