package venus

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class FuelDepotTest extends AnyFunSuite with Matchers {
  test("Can detect a valid password") {
    FuelDepot.isValidPassword(111111) shouldBe true
  }

  test("Can detect comprehensive valid passwords") {
    FuelDepot.isValidPassword(112233, onlyTwoAdjacent = true) shouldBe true
    FuelDepot.isValidPassword(123444, onlyTwoAdjacent = true) shouldBe false
    FuelDepot.isValidPassword(111122, onlyTwoAdjacent = true) shouldBe true
  }

  test("Returns false if any of the numbers decrease") {
    FuelDepot.isValidPassword(223450) shouldBe false
  }

  test("Returns false if there is no repeating digit in the password") {
    FuelDepot.isValidPassword(123789) shouldBe false
  }


  test("Can determine if a byte array has adjacent digits") {
    FuelDepot.hasAdjacentDigits(Vector[Byte](1, 1, 1, 1, 1)) shouldBe true
    FuelDepot.hasAdjacentDigits(Vector[Byte](1, 2, 3, 3, 5)) shouldBe true
    FuelDepot.hasAdjacentDigits(Vector[Byte](1, 2, 3, 4, 5)) shouldBe false
  }

  test("Can determine if a byte array is in incrementing order") {
    FuelDepot.hasNoDecreasingDigits(Vector[Byte](1, 1, 1, 1, 1)) shouldBe true
    FuelDepot.hasNoDecreasingDigits(Vector[Byte](1, 2, 3, 4, 5)) shouldBe true
    FuelDepot.hasNoDecreasingDigits(Vector[Byte](1, 2, 3, 3, 5)) shouldBe true

    FuelDepot.hasNoDecreasingDigits(Vector[Byte](1, 2, 3, 6, 5)) shouldBe false
    FuelDepot.hasNoDecreasingDigits(Vector[Byte](1, 1, 2, 1, 1)) shouldBe false
  }
}
