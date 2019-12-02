package day1

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class FuelCounterUpperTest extends AnyFunSuite with Matchers {
  test("Calculates the total amount of fuel requires for a list of modules") {
    val modules = Vector(
      Module(mass = 100, fuel = 100),
      Module(mass = 200, fuel = 200),
      Module(mass = 300, fuel = 300)
    )

    FuelCounterUpper.totalFuelFor(modules) shouldBe 600
  }
}
