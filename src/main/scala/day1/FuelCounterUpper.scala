package day1

import scala.util.Try


object FuelCounterUpper {
  val DivideMassBy = 3
  val SubtractFromMass = 2

  def fuelRequiredFor(module: Module): Int = (Math.floor(module.mass / DivideMassBy) - SubtractFromMass).toInt

  def fuelRequired: Try[Int] = ShipModules.modules.map(fuelRequiredFor)

  def fuelRequiredFor(moduleMasses: Seq[Module]): Int = moduleMasses.map(fuelRequiredFor).sum
}
