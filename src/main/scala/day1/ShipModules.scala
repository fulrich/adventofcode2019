package day1

import scala.io.Source
import scala.util.Try


object ShipModules {
  val ModuleMassResource = "day1/module_masses.txt"


  def modules: Try[Seq[Module]] = modules(ModuleMassResource)

  def modules(resourceName: String): Try[Seq[Module]] =
    Try(Source.fromResource(resourceName).getLines.toVector).flatMap(modules)

  def modules(moduleMasses: Seq[String]): Try[Seq[Module]] =
    Try(moduleMasses.map(moduleMass => Module(moduleMass.toInt)))
}
