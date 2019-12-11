package ship.hull

object PaintPrinter {
  def apply(points: Seq[PaintedPoint]): Unit = {
    val pointsInRows = points.groupBy(_.point.y).toVector
    val sortedRows = pointsInRows.sortBy(_._1)

    sortedRows.foreach(row => println(buildRowOutput(row._2)))
  }

  def printRow(row: Seq[PaintedPoint]): Unit = println(buildRowOutput(row))

  def buildRowOutput(row: Seq[PaintedPoint]): String = {
    val sortedPoints = row.sortBy(_.point.x)
    val startString = " " * sortedPoints.head.point.x + pointPrint(sortedPoints.head)

    sortedPoints.sliding(2).foldLeft(startString) { case (output, Seq(leftPoint, rightPoint)) =>
      output + (" " * (rightPoint.point.x - leftPoint.point.x - 1)) + pointPrint(rightPoint)
    }
  }

  def pointPrint(point: PaintedPoint): String = point.colour match {
    case Paint.White => "#"
    case Paint.Black => "."
  }
}
