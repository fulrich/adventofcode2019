package day4

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PasswordDeterminerTest extends AnyFunSuite with Matchers {
  test("Can detect a valid password") {
    PasswordDeterminer.isValidPassword(111111) shouldBe true
  }

  test("Can detect comprehensive valid passwords") {
    PasswordDeterminer.isValidComprehensivePassword(112233) shouldBe true
    PasswordDeterminer.isValidComprehensivePassword(123444) shouldBe false
    PasswordDeterminer.isValidComprehensivePassword(111122) shouldBe true
  }

  test("Returns false if any of the numbers decrease") {
    PasswordDeterminer.isValidPassword(223450) shouldBe false
  }

  test("Returns false if there is no repeating digit in the password") {
    PasswordDeterminer.isValidPassword(123789) shouldBe false
  }


  test("Can determine if a byte array has adjacent digits") {
    PasswordDeterminer.hasAdjacentDigits(Vector[Byte](1, 1, 1, 1, 1)) shouldBe true
    PasswordDeterminer.hasAdjacentDigits(Vector[Byte](1, 2, 3, 3, 5)) shouldBe true
    PasswordDeterminer.hasAdjacentDigits(Vector[Byte](1, 2, 3, 4, 5)) shouldBe false
  }

  test("Can determine if a byte array is in incrementing order") {
    PasswordDeterminer.hasNoDecreasingDigits(Vector[Byte](1, 1, 1, 1, 1)) shouldBe true
    PasswordDeterminer.hasNoDecreasingDigits(Vector[Byte](1, 2, 3, 4, 5)) shouldBe true
    PasswordDeterminer.hasNoDecreasingDigits(Vector[Byte](1, 2, 3, 3, 5)) shouldBe true

    PasswordDeterminer.hasNoDecreasingDigits(Vector[Byte](1, 2, 3, 6, 5)) shouldBe false
    PasswordDeterminer.hasNoDecreasingDigits(Vector[Byte](1, 1, 2, 1, 1)) shouldBe false
  }
}
