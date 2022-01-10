package example.substitution.polygraphic

import example.Alphabets.alphabets
import example.Cipher

import scala.annotation.tailrec

class PlayFairCipher(key: String) extends Cipher {

  private val alphabetsWithoutJ = "ABCCDEFGHIKLMNOPQRSTUVWXYZ"
  private val keyWithoutJ: String = key.replace('J', 'I').toUpperCase()
  private val keyword = (keyWithoutJ + alphabetsWithoutJ).foldLeft("")((keyword, cur) => {
    if (!keyword.contains(cur))
      keyword + cur else keyword
  })

  private val playfairSquare = keyword
    .zipWithIndex
    .foldLeft(Map[Char, (Int, Int)]())((res, cur) => {
      val charLoc = (cur._2 / 5, cur._2 % 5)
      cur._1 match {
        case 'I' => res + (cur._1 -> charLoc) +  ('J' -> charLoc)
        case _ => res + (cur._1 -> charLoc)
      }
    })

  override def encipher(plainText: String): String = {
    @tailrec
    def e(p: String, c: String = ""): String = {
      if (p.isEmpty) return c
      val charsRead = p match {
        case a if a.length == 1 => (1, a(0) + "X")
        case a if a(0) == a(1) => (1, a(0) + "X");
        case a => (2, a.take(2))
      }
      e(p.substring(charsRead._1),
        c + scramble(charsRead._2.toUpperCase()))
    }

    e(plainText.replaceAllLiterally(" ", ""))
  }

  private def getNext(num: Int): Int = (num + 1) % 5

  private def getPrevious(num: Int): Int = num match {
    case 0 => 4
    case _ => (num - 1) % 5
  }

  private def getLoc(row: Int, col: Int): Int = (row * 5) + col

  private def jumpColumn(row: Int, column: Int): Int = getLoc(row, getNext(column))

  private def jumpToPrevColumn(row: Int, column: Int): Int = getLoc(row, getPrevious(column))

  private def jumpRow(row: Int, column: Int): Int = getLoc(getNext(row), column)

  private def jumpToPrevRow(row: Int, column: Int): Int = getLoc(getPrevious(row), column)

  private def getNextInRow(row: Int, col: Int): Char = keyword.charAt(jumpColumn(row, col))

  private def getPrevInRow(row: Int, col: Int): Char = keyword.charAt(jumpToPrevColumn(row, col))

  private def getNextInCol(row: Int, col: Int): Char = keyword.charAt(jumpRow(row, col))

  private def getPrevInCol(row: Int, col: Int): Char = keyword.charAt(jumpToPrevRow(row, col))

  private def getCharIn(row: Int, col: Int) = keyword.charAt(getLoc(row, col))

  private def scramble(pair: String): String = {
    if (!alphabets.contains(pair(0)) || !alphabets.contains(pair(1))) return pair
    val (firstCharRow, firstCharCol) = playfairSquare(pair(0))
    val (secondCharRow, secondCharCol) = playfairSquare(pair(1))
    if (firstCharRow == secondCharRow) {
      "" + getNextInRow(firstCharRow, firstCharCol) + getNextInRow(secondCharRow, secondCharCol)
    } else if (firstCharCol == secondCharCol)
      "" + getNextInCol(firstCharRow, firstCharCol) + getNextInCol(secondCharRow, secondCharCol)
    else
      "" + getCharIn(firstCharRow, secondCharCol) + getCharIn(secondCharRow, firstCharCol)
  }

  private def descramble(pair: String): String = {
    if (!alphabets.contains(pair(0)) || !alphabets.contains(pair(1))) return pair
    val (firstCharRow, firstCharCol) = playfairSquare(pair(0))
    val (secondCharRow, secondCharCol) = playfairSquare(pair(1))
    if (firstCharRow == secondCharRow) {
      "" + getPrevInRow(firstCharRow, firstCharCol) + getPrevInRow(secondCharRow, secondCharCol)
    } else if (firstCharCol == secondCharCol)
      "" + getPrevInCol(firstCharRow, firstCharCol) + getPrevInCol(secondCharRow, secondCharCol)
    else
      "" + getCharIn(firstCharRow, secondCharCol) + getCharIn(secondCharRow, firstCharCol)
  }

  override def decipher(enciphered: String): String = {
    @tailrec
    def d(p: String, c: String = ""): String = {
      if (p.isEmpty) return c
      val charsRead = p match {
        case a if a.length == 1 => (1, a(0) + "X")
        case a if a(0) == a(1) => (1, a(0) + "X");
        case a => (2, a.take(2))
      }
      d(p.substring(charsRead._1),
        c + descramble(charsRead._2.toUpperCase()).toLowerCase())
    }

    d(enciphered.replaceAllLiterally(" ", ""))
  }
}
