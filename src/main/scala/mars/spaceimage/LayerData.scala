package mars.spaceimage


case class LayerData(lines: Seq[LineData]) {
  def numberOfDigits(numberToCount: Int): Int = lines.map(_.numberOfDigits(numberToCount)).sum

  def overlay(layer: LayerData): LayerData = LayerData(
    (lines zip layer.lines) .map { case (topLayer, bottomLayer) =>
      topLayer.overlay(bottomLayer)
    }
  )

  def print(): Unit = lines.foreach(_.print())
}

object LayerData {
  def apply(lines: Iterator[LineData]): LayerData = LayerData(lines.toVector)
  def fromLines(lines: LineData*): LayerData = apply(lines)
}
