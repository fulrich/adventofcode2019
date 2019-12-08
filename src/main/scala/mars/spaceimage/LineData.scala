package mars.spaceimage


case class LineData(pixels: Seq[Int]) {
  def numberOfDigits(numberToCount: Int): Int = pixels.count(_ == numberToCount)

  def overlay(line: LineData): LineData = LineData (
    (pixels zip line.pixels).map { case (topLayer, bottomLayer) =>
      if (topLayer == Pixel.Transparent) bottomLayer
      else topLayer
    }
  )

  def print(): Unit = println(pixels.map(Pixel.toString).mkString)
}

object LineData {
  def pixels(pixels: Int*): LineData = LineData(pixels.toVector)
}
