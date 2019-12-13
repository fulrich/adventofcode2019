package grids


class GridPrinter[A](points: Seq[A])(point: A => PointLike) {
  def apply(print: A => String): Unit = {
    val pointsInRows = points.groupBy(point(_).y).toVector
    val sortedRows = pointsInRows.sortBy(_._1)

    sortedRows.foreach(row => println(buildRowOutput(row._2, print)))
  }

  def printRow(row: Seq[A])(print: A => String): Unit = println(buildRowOutput(row, print))

  def buildRowOutput(row: Seq[A], print: A => String): String = {
    val sortedPoints = row.sortBy(point(_).x)
    val startString = " " * point(sortedPoints.head).x + print(sortedPoints.head)

    if(sortedPoints.length > 1) {
      sortedPoints.sliding(2).foldLeft(startString) { case (output, Seq(leftPoint, rightPoint)) =>
        output + (" " * (point(rightPoint).x - point(leftPoint).x - 1)) + print(rightPoint)
      }
    } else {
      print(sortedPoints.head)
    }
  }
}
