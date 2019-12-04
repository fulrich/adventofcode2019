package day3

case class Line(point1: Point, point2: Point) {
  lazy val allPoints: Seq[Point] =
    if(isHorizontal) xRange.toVector.map(x => Point(x, point1.y))
    else yRange.toVector.map(y => Point(point1.x, y))

  lazy val xRange: Range = Math.min(point1.x, point2.x) to Math.max(point1.x, point2.x)
  lazy val yRange: Range = Math.min(point1.y, point2.y) to Math.max(point1.y, point2.y)

  lazy val isHorizontal: Boolean = point1.y == point2.y
  lazy val isVertical: Boolean = point1.x == point2.x
}
