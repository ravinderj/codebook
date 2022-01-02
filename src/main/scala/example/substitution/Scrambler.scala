package example.substitution

trait Scrambler {
  def scramble(char: Char, index: Int): Char

  def unscramble(char: Char, index: Int): Char
}
