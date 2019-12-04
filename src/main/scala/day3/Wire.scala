package day3

case class Wire(segments: Seq[Line] = Vector.empty) {
  lazy val allPoints: Seq[Point] = segments.flatMap(_.allPoints).distinct

  def intersectionPoints(wire: Wire): Seq[Point] = allPoints.intersect(wire.allPoints).filterNot(Point.CentralPort.==)

  def addSegment(direction: WireDirection): Wire = {
    val lastPoint = segments.lastOption.map(_.point2).getOrElse(Point.CentralPort)
    copy(segments = segments :+ direction.lineFrom(lastPoint))
  }
}

object Wire {
  val Empty: Wire = Wire()
  val Separator: String = ","

  def fromLines(lines: Line*): Wire = Wire(lines.toVector)

  def fromRaw(directions: String): Wire =
    fromRawDirections(directions.split(Separator))

  def fromRawDirections(directions: Seq[String]): Wire =
    fromDirections(directions.map(WireDirection.apply))

  def fromDirections(directions: Seq[WireDirection]): Wire =
    directions.foldLeft(Empty) { case (wire, direction) => wire.addSegment(direction) }
}
