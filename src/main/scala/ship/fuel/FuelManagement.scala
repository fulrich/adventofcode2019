package ship.fuel

import ship.panel.WirePanel
import ship.panel.parts.Wire

import scala.io.Source
import scala.util.Try


object FuelManagement {
  val fuelManagementPanelWires = "ship/fuel/wire_panel.txt"


  lazy val Panel: Try[WirePanel] = for {
    wires <- WireList
    wire1 = wires.head
    wire2 = wires.last
  } yield WirePanel(wire1, wire2)

  lazy val WireList: Try[Seq[Wire]] = from(fuelManagementPanelWires)

  def from(resourceName: String): Try[Seq[Wire]] =
    raw(resourceName).map(rawWireDirections => rawWireDirections.map(Wire.fromRaw))

  def raw(resourceName: String): Try[Seq[String]] =
    Try(Source.fromResource(resourceName).getLines.toVector)
}
