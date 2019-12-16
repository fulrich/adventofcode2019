package ship.oxygen


sealed trait Tile {
  val isBlocked: Boolean = false
  val canEnter: Boolean = true
  val symbol: String
}

object Tile {
  case object Unexplored extends Tile {
    override val symbol = "?"
  }
  case object Open extends Tile {
    override val symbol = " "
  }
  case object OxygenSystem extends Tile {
    override val symbol = "*"
  }
  case object Wall extends  Tile {
    override val isBlocked: Boolean = true
    override val canEnter: Boolean = false
    override val symbol = "#"
  }

  def apply(repairOutput: Long): Tile = repairOutput match {
    case 0 => Tile.Wall
    case 1 => Tile.Open
    case 2 => Tile.OxygenSystem
    case _ => Tile.Unexplored
  }
}
