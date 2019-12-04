package day4


object PasswordDeterminer {
  val PasswordRange: Range = 264793 to 803935
  val FillerDigit: Byte = -1


  def validPasswords(onlyTwoAdjacent: Boolean = false): Seq[Int] =
    validPasswordsWithin(PasswordRange, onlyTwoAdjacent)

  def validPasswordsWithin(range: Range, onlyTwoAdjacent: Boolean = false): Seq[Int] =
    range.filter(isValidPassword(_, onlyTwoAdjacent))

  def isValidPassword(password: Int, onlyTwoAdjacent: Boolean = false): Boolean = {
    val passwordByteArray: Seq[Byte] = password.toString.map(_.toByte)

    hasAdjacentDigits(passwordByteArray) &&
      hasNoDecreasingDigits(passwordByteArray) &&
      (if (onlyTwoAdjacent) hasOnlyTwoAdjacentDigits(passwordByteArray) else true)
  }


  def hasAdjacentDigits(digits: Seq[Byte]): Boolean = digits.sliding(2).exists {
    case Seq(first, second) => first == second
  }

  def hasNoDecreasingDigits(digits: Seq[Byte]): Boolean = digits.sliding(2).forall {
    case Seq(first, second) => first <= second
  }

  def hasOnlyTwoAdjacentDigits(digits: Seq[Byte]): Boolean = (FillerDigit +: digits :+ FillerDigit).sliding(4).exists {
    case Seq(a, b, c, d) => a != b && b == c && c != d
  }
}
