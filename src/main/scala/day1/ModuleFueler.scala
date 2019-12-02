package day1

import scala.annotation.tailrec

object ModuleFueler {
  val DivideMassBy = 3
  val SubtractFromMass = 2

  @tailrec
  def fuelForModuleMass(mass: Int, accumulatedFuel: Int = 0): Int = {
    val requiredFuel = Math.max(fuelForMass(mass), 0)

    if(requiredFuel == 0) accumulatedFuel
    else fuelForModuleMass(requiredFuel, accumulatedFuel + requiredFuel)
  }

  def fuelForMass(mass: Int): Int = (Math.floor(mass / DivideMassBy) - SubtractFromMass).toInt

  def fuelUpModule(module: Module): Module = module.copy(fuel = fuelForModuleMass(module.mass))

  def fuelUpModules(modules: Seq[Module]): Seq[Module] = modules.map(fuelUpModule)
}
