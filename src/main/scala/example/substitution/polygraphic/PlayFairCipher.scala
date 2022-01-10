package example.substitution.polygraphic

import example.Cipher

import scala.annotation.tailrec

class PlayFairCipher(key: String) extends Cipher {

  private val alphabets = "ABCCDEFGHIKLMNOPQRSTUVWXYZ"
  private val upperCasedKey = key.toUpperCase()
  private val keyword = (upperCasedKey + alphabets).foldLeft("")((keyword, cur) =>
    if (!keyword.contains(cur))
      keyword + cur else keyword)

  private val playfairSquare = keyword
    .zipWithIndex
    .foldLeft(Map[Char, (Int, Int)]())((res, cur) => {
      res + (cur._1 -> (cur._2 / 5, cur._2 % 5))
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

  private def getLoc(row: Int, col: Int): Int = (row * 5) + col

  private def jumpColumn(row: Int, column: Int): Int = getLoc(row, getNext(column))

  private def jumpRow(row: Int, column: Int): Int = getLoc(getNext(row), column)

  private def getNextInRow(row: Int, col: Int): Char = keyword.charAt(jumpColumn(row, col))

  private def getNextInCol(row: Int, col: Int): Char = keyword.charAt(jumpRow(row, col))

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

  override def decipher(cipherText: String): String = ???
}
