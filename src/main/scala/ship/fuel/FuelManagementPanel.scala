package ship.fuel

import ship.panel.Wire

import scala.io.Source
import scala.util.Try

object FuelManagementPanel {
  val fuelManagementPanelWires = "ship/fuel/wire_panel.txt"


  def wireList: Try[Seq[Wire]] = from(fuelManagementPanelWires)

  def from(resourceName: String): Try[Seq[Wire]] =
    raw(resourceName).map(rawWireDirections => rawWireDirections.map(Wire.fromRaw))

  def raw(resourceName: String): Try[Seq[String]] =
    Try(Source.fromResource(resourceName).getLines.toVector)
}
