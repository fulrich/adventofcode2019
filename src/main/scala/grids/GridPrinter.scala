package grids


class GridPrinter[A](points: Seq[A])(point: A => PointLike) {
  def apply(print: A => String): Unit = {
    val pointsInRows = points.groupBy(point(_).y).toVector
    val sortedRows = pointsInRows.sortBy(_._1)
    val startPoint = points.map(p => point(p).x).min

    sortedRows.foreach(row => println(buildRowOutput(startPoint, row._2, print)))
  }

  def printRow(startPoint: Int, row: Seq[A])(print: A => String): Unit = println(buildRowOutput(startPoint, row, print))

  def buildRowOutput(startPoint: Int, row: Seq[A], print: A => String): String = {
    val sortedPoints = row.sortBy(point(_).x)
    val startString = " " * math.abs(startPoint - point(sortedPoints.head).x)

    if(sortedPoints.length > 1) {
      sortedPoints.sliding(2).foldLeft(startString) { case (output, Seq(leftPoint, rightPoint)) =>
        output + (" " * math.abs(point(rightPoint).x - point(leftPoint).x - 1)) + print(rightPoint)
      }
    } else {
      print(sortedPoints.head)
    }
  }
}
