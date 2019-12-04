package day3

import scala.io.Source
import scala.util.Try

object FuelManagementPanel {
  val fuelManagementPanelWires = "day3/wire_directions.txt"


  def wireList: Try[Seq[Wire]] = from(fuelManagementPanelWires)

  def from(resourceName: String): Try[Seq[Wire]] =
    raw(resourceName).map(rawWireDirections => rawWireDirections.map(Wire.fromRaw))

  def raw(resourceName: String): Try[Seq[String]] =
    Try(Source.fromResource(resourceName).getLines.toVector)
}
