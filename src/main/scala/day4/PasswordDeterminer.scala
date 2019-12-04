package day4

object PasswordDeterminer {
  val PasswordRange: Range = 264793 to 803935


  def validPasswords(onlyTwoAdjacent: Boolean = false): Seq[Int] =
    validPasswordsWithin(PasswordRange, onlyTwoAdjacent)

  def validPasswordsWithin(range: Range, onlyTwoAdjacent: Boolean = false): Seq[Int] =
    range.filter(isValidPassword(_, onlyTwoAdjacent))

  def isValidPassword(password: Int, onlyTwoAdjacent: Boolean = false): Boolean = {
    val passwordByteArray: Seq[Byte] = password.toString.map(_.toByte)

    hasAdjacentDigits(passwordByteArray) &&
      hasNoDecreasingDigits(passwordByteArray) &&
      (if (onlyTwoAdjacent) adjacentIsOnlyTwo(passwordByteArray) else true)
  }


  def hasAdjacentDigits(password: Seq[Byte]): Boolean = slidingWindow(password).foldLeft(false) {
    case (hasAdjacent, Seq(first, second)) => hasAdjacent || first == second
  }

  def hasNoDecreasingDigits(password: Seq[Byte]): Boolean = slidingWindow(password).foldLeft(true) {
    case (hasDecreasing, Seq(first, second)) => hasDecreasing && first <= second
  }

  def slidingWindow(password: Seq[Byte]): Iterator[Seq[Byte]] = password.sliding(2)


  def adjacentIsOnlyTwo(password: Seq[Byte]): Boolean =
    innerAdjacentIsOnlyTwo(password) ||
      (password.take(2).distinct.length == 1 && password.take(3).distinct.length != 1) ||
      (password.takeRight(2).distinct.length == 1 && password.takeRight(3).distinct.length != 1)

  def innerAdjacentIsOnlyTwo(password: Seq[Byte]): Boolean = password.sliding(4).foldLeft(false) {
    case (_, Seq(a, b, c, d)) if b == c && b != a && b != d => true
    case (includesDuplicate, _) => includesDuplicate
  }
}
