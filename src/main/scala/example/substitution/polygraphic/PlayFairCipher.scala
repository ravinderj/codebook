package example.substitution.polygraphic

import example.Alphabets.alphabets
import example.substitution.{PolygraphicScrambler, PolygraphicSubstitutionCipher}

class PlayFairCipher(key: String) extends PolygraphicSubstitutionCipher {
  val scrambler = new PlayFairScrambler(key)
}

class PlayFairScrambler(key: String) extends PolygraphicScrambler {
  private val alphabetsWithoutJ = "ABCCDEFGHIKLMNOPQRSTUVWXYZ"
  private val keyWithoutJ: String = key
    .replace('J', 'I')
    .toUpperCase()
  private val keyword = (keyWithoutJ + alphabetsWithoutJ)
    .foldLeft("")((keyword, cur) => {
      if (!keyword.contains(cur))
        keyword + cur else keyword
    })

  private val playfairSquare = keyword
    .zipWithIndex
    .foldLeft(Map[Char, (Int, Int)]())((res, cur) => {
      val charLoc = (cur._2 / 5, cur._2 % 5)
      cur._1 match {
        case 'I' => res + ('I' -> charLoc) + ('J' -> charLoc)
        case _ => res + (cur._1 -> charLoc)
      }
    })

  override def scramble(pair: (Char, Char)): String = {
    runScramblingRule(pair, new ScrambleRules(keyword))
  }

  override def unscramble(pair: (Char, Char)): String = {
    runScramblingRule(pair, new UnscrambleRules(keyword))
  }

  private def runScramblingRule(pair: (Char, Char), rules: PlayFairRules): String = {
    if (!alphabets.contains(pair._1) || !alphabets.contains(pair._2)) return pair._1 + pair._2.toString
    val (firstCharRow, firstCharCol) = playfairSquare(pair._1)
    val (secondCharRow, secondCharCol) = playfairSquare(pair._2)
    if (firstCharRow == secondCharRow)
      rules.row(firstCharRow, firstCharCol) + rules.row(secondCharRow, secondCharCol).toString
    else if (firstCharCol == secondCharCol)
      rules.column(firstCharRow, firstCharCol) + rules.column(secondCharRow, secondCharCol).toString
    else
      rules.diagonal(firstCharRow, secondCharCol) + rules.diagonal(secondCharRow, firstCharCol).toString
  }
}

trait PlayFairRules {
  def row(row: Int, col: Int): Char

  def column(row: Int, col: Int): Char

  def diagonal(row: Int, col: Int): Char

  protected def getLoc(row: Int, col: Int): Int = (row * 5) + col
}

class ScrambleRules(keyword: String) extends PlayFairRules {
  override def row(row: Int, col: Int): Char = keyword.charAt(jumpToNextColumn(row, col))

  override def column(row: Int, col: Int): Char = keyword.charAt(jumpToNextRow(row, col))

  override def diagonal(row: Int, col: Int): Char = keyword.charAt(getLoc(row, col))

  private def getNext(num: Int): Int = (num + 1) % 5

  private def jumpToNextColumn(row: Int, column: Int): Int = getLoc(row, getNext(column))

  private def jumpToNextRow(row: Int, column: Int): Int = getLoc(getNext(row), column)
}

class UnscrambleRules(keyword: String) extends PlayFairRules {
  override def row(row: Int, col: Int): Char = keyword.charAt(jumpToPrevColumn(row, col))

  override def column(row: Int, col: Int): Char = keyword.charAt(jumpToPrevRow(row, col))

  override def diagonal(row: Int, col: Int): Char = keyword.charAt(getLoc(row, col))

  private def getPrevious(num: Int): Int = num match {
    case 0 => 4
    case _ => (num - 1) % 5
  }

  private def jumpToPrevColumn(row: Int, column: Int): Int = getLoc(row, getPrevious(column))

  private def jumpToPrevRow(row: Int, column: Int): Int = getLoc(getPrevious(row), column)
}
