package mars.spaceimage

case class SpaceImage(layers: Seq[LayerData]) {
  def layer(number: Int): LayerData = layers(number)

  def layerWithFewest(digit: Int): LayerData =
    layers.min(Ordering.by((layer: LayerData) => layer.numberOfDigits(digit)))

  def overlay: LayerData =
    layers.tail.foldLeft(layers.head) { case (overlayLayer, nextLayer) =>
      overlayLayer.overlay(nextLayer)
    }
}

object SpaceImage {
  def apply(iterator: Iterator[LayerData]): SpaceImage = SpaceImage(iterator.toVector)
  def fromLayers(layers: LayerData*): SpaceImage = SpaceImage(layers)
}
