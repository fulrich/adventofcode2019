package ship.fuel

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.Module


class FuelCounterUpperTest extends AnyFunSuite with Matchers {
  test("Calculates the total amount of fuel requires for a list of modules") {
    val modules = Vector(
      Module(mass = 100, fuel = 100),
      Module(mass = 200, fuel = 200),
      Module(mass = 300, fuel = 300)
    )

    FuelCounterUpper.countTotalFuel(modules) shouldBe 600
  }

  test("Can correctly calculate the needed fuel for a given module"){
    FuelCounterUpper.fuelForModule(Module(14)) shouldBe 2
    FuelCounterUpper.fuelForModule(Module(1969)) shouldBe 966
    FuelCounterUpper.fuelForModule(Module(100756)) shouldBe 50346
  }

  test("Can correctly calculate the needed fuel for a mass including the fuel mass itself"){
    FuelCounterUpper.fuelForMassAndFuel(14) shouldBe 2
    FuelCounterUpper.fuelForMassAndFuel(1969) shouldBe 966
    FuelCounterUpper.fuelForMassAndFuel(100756) shouldBe 50346
  }

  test("Can calculate fuel for a specific mass") {
    FuelCounterUpper.fuelForMass(12) shouldBe 2
    FuelCounterUpper.fuelForMass(1969) shouldBe 654
    FuelCounterUpper.fuelForMass(100756) shouldBe 33583
  }
}
