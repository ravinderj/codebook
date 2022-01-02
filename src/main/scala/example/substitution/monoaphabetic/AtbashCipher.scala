package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.{Scrambler, SubstitutionCipher}

class AtbashCipher() extends SubstitutionCipher {
  override val scrambler: Scrambler = AtbashScrambler
}

object AtbashScrambler extends Scrambler {
  private val substitute = alphabets.reverse.mkString("")

  override def scramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      substitute.charAt(alphabets.indexOf(char))
    } else char
  }

  override def unscramble(char: Char, index: Int): Char = scramble(char, index)
}
