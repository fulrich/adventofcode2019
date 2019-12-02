import day1.{FuelCounterUpper, ModuleFueler, ShipModules}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ProblemRunner extends AnyFunSuite with Matchers {
  test("Day 1 - Problem 1: How much fuel for all modules") {
    val modules = ShipModules.modules.get
    val fueledModules = modules.map(module => module.copy(fuel = ModuleFueler.fuelForMass(module.mass)))
    val requiredFuel = FuelCounterUpper.totalFuelFor(fueledModules)

    println(s"Day 1 - Problem 1 Answer: ${requiredFuel}")
  }

  test("Day 1 - Problem 2: How much fuel accounting for fuel weight") {
    val modules = ShipModules.modules.get
    val fueledModules = ModuleFueler.fuelUpModules(modules)
    val requiredFuel = FuelCounterUpper.totalFuelFor(fueledModules)

    println(s"Day 1 - Problem 2 Answer: ${requiredFuel}")
  }
}
