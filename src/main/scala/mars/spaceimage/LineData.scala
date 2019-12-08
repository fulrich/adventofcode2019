package mars.spaceimage


case class LineData(pixels: Seq[Int]) {
  def numberOfDigits(numberToCount: Int): Int = pixels.count(_ == numberToCount)
}

object LineData {
  def pixels(pixels: Int*): LineData = LineData(pixels.toVector)
}
