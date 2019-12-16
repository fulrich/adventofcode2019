package ship.oxygen

import grids.Point

sealed trait Movement {
  val command: Int
  def move(point: Point): Point
  def next(point: Point, area: RepairArea): Movement
}

object Movement {
  case object North extends Movement {
    override val command: Int = 1
    override def move(point: Point): Point = point.up(1)

    def next(point: Point, area: RepairArea): Movement =
      if(area.canEnter(West.move(point))) { West }
      else if(area.canEnter(North.move(point))) { North }
      else if(area.canEnter(East.move(point))) { East }
      else  { South}
  }

  case object South extends Movement {
    override val command: Int = 2
    override def move(point: Point): Point = point.down(1)

    def next(point: Point, area: RepairArea): Movement =
      if(area.canEnter(East.move(point))) { East }
      else if(area.canEnter(South.move(point))) { South }
      else if(area.canEnter(West.move(point))) { West }
      else  { North}
  }

  case object West extends Movement {
    override val command: Int = 3
    override def move(point: Point): Point = point.left(1)

    def next(point: Point, area: RepairArea): Movement =
      if(area.canEnter(South.move(point))) { South }
      else if(area.canEnter(West.move(point))) { West }
      else if(area.canEnter(North.move(point))) { North }
      else  { East}
  }

  case object East extends Movement {
    override val command: Int = 4
    override def move(point: Point): Point = point.right(1)

    def next(point: Point, area: RepairArea): Movement =
      if(area.canEnter(North.move(point))) { North }
      else if(area.canEnter(East.move(point))) { East }
      else if(area.canEnter(South.move(point))) { South }
      else  { West}
  }
}
