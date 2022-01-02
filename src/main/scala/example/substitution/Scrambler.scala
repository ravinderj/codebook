package example.substitution

import example.Alphabets.alphabets

trait Scrambler {
  def scramble(char: Char): Char
  def unscramble(char: Char): Char
}

class ShiftScrambler(shift: Int) extends Scrambler {
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