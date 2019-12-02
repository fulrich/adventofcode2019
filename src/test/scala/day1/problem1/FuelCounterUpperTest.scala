package day1.problem1

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Success

class FuelCounterUpperTest extends AnyFunSuite with Matchers {
  val Day1Problem1Answer = 3497399

  test("Can take a list of masses and convert to fuel values") {
    val masses = Vector("12", "14", "1969", "100756")
    val expected = Vector(2, 2, 654, 33583).sum

    FuelCounterUpper.fuelRequiredFor(masses) shouldBe expected
  }

  test("Calculates the required fuel based on module masses") {
    FuelCounterUpper.fuelRequired shouldBe a[Success[_]]
    FuelCounterUpper.fuelRequired.get shouldBe Day1Problem1Answer
  }
}
