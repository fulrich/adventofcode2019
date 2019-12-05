package ship.fuel

import ship.Module

import scala.annotation.tailrec


object FuelCounterUpper {
  val DivideMassBy = 3
  val SubtractFromMass = 2


  def countTotalFuel(modules: Seq[Module]): Int = modules.map(_.fuel).sum

  def fuelForMass(mass: Int): Int = (Math.floor(mass / DivideMassBy) - SubtractFromMass).toInt

  def fuelForModule(module: Module): Int = fuelForMassAndFuel(module.mass)

  @tailrec
  def fuelForMassAndFuel(mass: Int, accumulatedFuel: Int = 0): Int = {
    val requiredFuel = Math.max(fuelForMass(mass), 0)

    if(requiredFuel == 0) accumulatedFuel
    else fuelForMassAndFuel(requiredFuel, accumulatedFuel + requiredFuel)
  }
}
