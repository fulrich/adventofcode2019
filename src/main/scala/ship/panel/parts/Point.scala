package ship.panel.parts

case class Point(x: Int, y: Int) {
  def up(upIncrement: Int): Point = copy(y = y + upIncrement)
  def down(downDecrement: Int): Point = copy(y = y - downDecrement)

  def right(rightIncrement: Int): Point = copy(x = x + rightIncrement)
  def left(leftDecrement: Int): Point = copy(x = x - leftDecrement)

  def manhattanDistanceTo(point: Point): Int =
    Math.abs((Math.abs(x) - Math.abs(point.x)) + (Math.abs(y) - Math.abs(point.y)))
}

object Point {
  val CentralPort: Point = Point(0, 0)
}
