package example.substitution

import example.Alphabets.alphabets

trait Scrambler {
  def scramble(char: Char, index: Int): Char

  def unscramble(char: Char, index: Int): Char
}

class CaesarScrambler(shift: Int) extends Scrambler {
  override def scramble(char: Char, index: Int): Char = {
    if (!alphabets.contains(char.toString)) char
    else {
      val currentPosition = alphabets.indexOf(char.toString)
      val nextPosition = (currentPosition + shift) % alphabets.length
      alphabets(nextPosition).charAt(0)
    }
  }

  override def unscramble(char: Char, index: Int): Char = {
    if (!alphabets.contains(char.toString)) char
    else {
      val currentPosition = alphabets.indexOf(char.toString)
      val nextPosition = (currentPosition + (alphabets.length - shift)) % alphabets.length
      alphabets(nextPosition).charAt(0).toLower
    }
  }
}

object AtbashScrambler extends Scrambler {
  override def scramble(char: Char, index: Int): Char = {
    if (!alphabets.contains(char.toString)) char
    else {
      val currentPosition = alphabets.indexOf(char.toString) + 1
      val nextPosition = alphabets.length - currentPosition
      alphabets(nextPosition).charAt(0)
    }
  }

  override def unscramble(char: Char, index: Int): Char = scramble(char, index).toLower
}

class MixedAlphabetScrambler(key: String) extends Scrambler {
  private val upperCasedKey = key.toUpperCase()
  private val lookup = upperCasedKey + alphabets.filterNot(upperCasedKey.contains).mkString("")

  override def scramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char.toString)) {
      lookup.charAt(alphabets.indexOf(char.toUpper.toString))
    } else char
  }

  override def unscramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char.toString)) {
      alphabets(lookup.indexOf(char.toUpper)).charAt(0)
    } else char
  }
}

class VigenereScrambler(key: String) extends Scrambler {
  private val upperCasedKey = key.toUpperCase()
  private val shifts = upperCasedKey.map(k => alphabets.indexOf(k.toString))

  override def scramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char.toString)) {
      val shift = shifts(index % shifts.length)
      val currentPosition = alphabets.indexOf(char.toString)
      val nextPosition = (currentPosition + shift) % alphabets.length
      alphabets(nextPosition).charAt(0)
    } else char
  }

  override def unscramble(char: Char, index: Int): Char = {
    if (alphabets.contains(char.toString)) {
      val shift = shifts(index % shifts.length)
      val currentPosition = alphabets.indexOf(char.toString)
      val nextPosition = (currentPosition + (alphabets.length - shift)) % alphabets.length
      alphabets(nextPosition).charAt(0)
    } else char
  }
}
