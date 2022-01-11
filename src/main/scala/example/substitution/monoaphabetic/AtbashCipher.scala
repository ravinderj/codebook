package example.substitution.monoaphabetic

import example.Alphabets.alphabets
import example.substitution.{MonographicScrambler, MonographicSubstitutionCipher}

class AtbashCipher() extends MonographicSubstitutionCipher {
  override val scrambler: MonographicScrambler = AtbashScrambler
}

object AtbashScrambler extends MonographicScrambler {
  private val substitute = alphabets.reverse.mkString("")

  override def scramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char)) {
      substitute.charAt(alphabets.indexOf(char))
    } else char
  }

  override def unscramble(char: Char, index: Int): Char = scramble(char, index)
}
