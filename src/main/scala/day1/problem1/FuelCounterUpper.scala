package day1.problem1

import scala.io.Source
import scala.util.Try


object FuelCounterUpper {
  val DivideMassBy = 3
  val SubtractFromMass = 2

  def fuelRequired: Try[Int] = Try(fuelRequiredFor("day1/module_masses.txt"))

  def fuelRequiredFor(resourceName: String): Int = fuelRequiredFor(Source.fromResource(resourceName).getLines.toVector)

  def fuelRequiredFor(moduleMasses: Seq[String]): Int =
    moduleMasses
      .map (_.toInt)
      .map { moduleMass => (Math.floor(moduleMass / DivideMassBy) - SubtractFromMass).toInt }
      .sum
}
