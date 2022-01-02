package example.substitution

import example.Alphabets.alphabets

trait Scrambler {
  def scramble(char: Char): Char
  def unscramble(char: Char): Char
}

class CaesarScrambler(shift: Int) extends Scrambler {
  override def scramble(char: Char): Char = {
    if (!alphabets.contains(char.toString)) char
    else {
      val currentPosition = alphabets.indexOf(char.toString)
      val nextPosition = (currentPosition + shift) % alphabets.length
      alphabets(nextPosition).charAt(0)
    }
  }

  override def unscramble(char: Char): Char = {
    if (!alphabets.contains(char.toString)) char
    else {
      val currentPosition = alphabets.indexOf(char.toString)
      val nextPosition = (currentPosition + (alphabets.length - shift)) % alphabets.length
      alphabets(nextPosition).charAt(0).toLower
    }
  }
}

object AtbashScrambler extends Scrambler {
  override def scramble(char: Char): Char = {
    if (!alphabets.contains(char.toString)) char
    else {
      val currentPosition = alphabets.indexOf(char.toString) + 1
      val nextPosition = alphabets.length - currentPosition
      alphabets(nextPosition).charAt(0)
    }
  }

  override def unscramble(char: Char): Char = scramble(char).toLower
}

