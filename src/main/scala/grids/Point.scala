package grids


case class Point(x: Int, y: Int) extends PointLike {
  def up(upIncrement: Int): Point = copy(y = y + upIncrement)
  def down(downDecrement: Int): Point = copy(y = y - downDecrement)

  def right(rightIncrement: Int): Point = copy(x = x + rightIncrement)
  def left(leftDecrement: Int): Point = copy(x = x - leftDecrement)
}

object Point {
  val Origin: Point = Point(0, 0)
}