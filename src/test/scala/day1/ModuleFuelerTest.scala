package day1

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class ModuleFuelerTest extends AnyFunSuite with Matchers {
  test("Can correctly calculate the needed fuel for a given module mass"){
    ModuleFueler.fuelForModuleMass(14) shouldBe 2
    ModuleFueler.fuelForModuleMass(1969) shouldBe 966
    ModuleFueler.fuelForModuleMass(100756) shouldBe 50346
  }

  test("Can fuel up a module") {
    ModuleFueler.fuelUpModule(Module(mass = 14)) shouldBe Module(mass = 14, fuel = 2)
    ModuleFueler.fuelUpModule(Module(mass = 1969)) shouldBe Module(mass = 1969, fuel = 966)
    ModuleFueler.fuelUpModule(Module(mass = 100756)) shouldBe Module(mass = 100756, fuel = 50346)
  }

  test("Can calculate fuel for a specific mass") {
    ModuleFueler.fuelForMass(12) shouldBe 2
    ModuleFueler.fuelForMass(1969) shouldBe 654
    ModuleFueler.fuelForMass(100756) shouldBe 33583
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
}
