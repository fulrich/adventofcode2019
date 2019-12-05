package ship.fuel

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.Module


class ModuleFuelerTest extends AnyFunSuite with Matchers {
  test("Can fuel up a module") {
    ModuleFueler.fuelUpModule(Module(mass = 14)) shouldBe Module(mass = 14, fuel = 2)
    ModuleFueler.fuelUpModule(Module(mass = 1969)) shouldBe Module(mass = 1969, fuel = 966)
    ModuleFueler.fuelUpModule(Module(mass = 100756)) shouldBe Module(mass = 100756, fuel = 50346)
  }

  test ("Can fuel up a list of modules") {
    val modules = Vector(
      Module(mass = 14),
      Module(mass = 1969),
      Module(mass = 100756)
    )
    val expected = Vector(
      Module(mass = 14, fuel = 2),
      Module(mass = 1969, fuel = 966),
      Module(mass = 100756, fuel = 50346)
    )

    ModuleFueler.fuelUpModules(modules) shouldBe expected
  }

  test("Can fuel up a module not accounting for the weight of the fuel") {
    ModuleFueler.fuelUpModuleIgnoringFuelWeight(Module(mass = 14)) shouldBe Module(mass = 14, fuel = 2)
    ModuleFueler.fuelUpModuleIgnoringFuelWeight(Module(mass = 1969)) shouldBe Module(mass = 1969, fuel = 654)
    ModuleFueler.fuelUpModuleIgnoringFuelWeight(Module(mass = 100756)) shouldBe Module(mass = 100756, fuel = 33583)
  }

  test("Can fuel up a list of modules not accounting for the weight of the fuel") {
    val modules = Vector(
      Module(mass = 14),
      Module(mass = 1969),
      Module(mass = 100756)
    )
    val expected = Vector(
      Module(mass = 14, fuel = 2),
      Module(mass = 1969, fuel = 654),
      Module(mass = 100756, fuel = 33583)
    )

    ModuleFueler.fuelUpModulesIgnoringFuelWeight(modules) shouldBe expected
  }
}
