package mars.spaceimage


case class LayerData(number: Int, lines: Seq[LineData]) {
  def numberOfDigits(numberToCount: Int): Int = lines.map(_.numberOfDigits(numberToCount)).sum
}

object LayerData {
  def apply(number: Int, lines: Iterator[LineData]): LayerData = LayerData(number, lines.toVector)
  def fromLines(number: Int, lines: LineData*): LayerData = apply(number, lines)
}
