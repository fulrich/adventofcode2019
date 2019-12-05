package ship.panel

sealed abstract class WireDirection {
  def distance: Int
  def lineFrom(point: Point): Line
}

object WireDirection {
  case class Up(distance: Int) extends WireDirection {
    override def lineFrom(point: Point): Line = Line(point, point.up(distance))
  }

  case class Down(distance: Int) extends WireDirection {
    override def lineFrom(point: Point): Line = Line(point, point.down(distance))
  }

  case class Left(distance: Int) extends WireDirection {
    override def lineFrom(point: Point): Line = Line(point, point.left(distance))
  }

  case class Right(distance: Int) extends WireDirection{
    override def lineFrom(point: Point): Line = Line(point, point.right(distance))
  }

  def apply(direction: String): WireDirection = direction.head match {
    case 'U' => Up(direction.tail.toInt)
    case 'D' => Down(direction.tail.toInt)
    case 'R' => Right(direction.tail.toInt)
    case 'L' => Left(direction.tail.toInt)
  }
}
