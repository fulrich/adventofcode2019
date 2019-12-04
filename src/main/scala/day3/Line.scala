package day3

case class Line(point1: Point, point2: Point) {
  lazy val allPoints: Seq[Point] =
    if(isHorizontal) withDirectionality(point1.x > point2.x) { xRange.toVector.map(horizontalPoint) }
    else withDirectionality(point1.y > point2.y) { yRange.toVector.map(verticalPoint) }

  lazy val xRange: Range = Math.min(point1.x, point2.x) to Math.max(point1.x, point2.x)
  lazy val yRange: Range = Math.min(point1.y, point2.y) to Math.max(point1.y, point2.y)

  def horizontalPoint(x: Int ): Point = Point(x, point1.y)
  def verticalPoint(y: Int ): Point = Point(point1.x, y)

  lazy val isHorizontal: Boolean = point1.y == point2.y
  lazy val isVertical: Boolean = point1.x == point2.x

  private def withDirectionality(isNegativeDirection: Boolean)(f: => Seq[Point]) =
    if(isNegativeDirection) f.reverse else f
}
