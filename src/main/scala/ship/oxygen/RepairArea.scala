package ship.oxygen

import grids.{GridPrinter, Point}


case class RepairArea(unexplored: Seq[Point], explored: Map[Point, Tile]) {
  def registerExplored(point: Point, tile: Tile): RepairArea = copy(
    explored = explored + (point -> tile),
    unexplored = unexplored ++ unexploredAround(point, tile)
  )

  def unexploredAround(point: Point, tile: Tile): Seq[Point] = tile match {
    case Tile.Wall => Vector.empty
    case Tile.Open | Tile.OxygenSystem => unexploredPointsAroundNonWall(point)
  }

  def unexploredPointsAroundNonWall(point: Point): Seq[Point] = Vector(
    pointIfUnexplored(point.up(1)),
    pointIfUnexplored(point.down(1)),
    pointIfUnexplored(point.left(1)),
    pointIfUnexplored(point.right(1))
  ).flatten

  def pointIfUnexplored(point: Point): Option[Point] = Some(point).filter(!explored.isDefinedAt(_))

  def canEnter(point: Point): Boolean = explored.getOrElse(point, Tile.Unexplored).canEnter

  lazy val fullyExplored: Boolean = unexplored.isEmpty && explored.nonEmpty

  def print(): Unit  = {
    val printer = new GridPrinter(explored.toVector)(_._1)
    printer(_._2.symbol)
  }
}

object RepairArea {
  val Empty: RepairArea = RepairArea(unexplored = Vector.empty, explored = Map.empty)

  def startAt(point: Point): RepairArea = RepairArea.Empty.registerExplored(point, Tile.Open)
}
