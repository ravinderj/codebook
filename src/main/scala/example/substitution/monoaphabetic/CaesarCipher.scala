package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.{Scrambler, SubstitutionCipher}

class CaesarCipher(shift: Int) extends SubstitutionCipher {
  val scrambler = new CaesarScrambler(shift)
}

class CaesarScrambler(shift: Int) extends Scrambler {
  private val substitute = (
    alphabets.takeRight(alphabets.length - shift)
      ++ alphabets.take(shift)
    ).mkString("")

  override def scramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      substitute.charAt(alphabets.indexOf(char))
    } else char
  }

  override def unscramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      alphabets(substitute.indexOf(char))
    } else char
  }
}
