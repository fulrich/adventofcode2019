package mars

import mars.spaceimage.{LayerData, LineData, SpaceImage}


case class SpaceImageParser(width: Int, height: Int) {
  val LayerSize: Int = width * height

  def from(pixels: String): SpaceImage = from(pixels.toVector.map(_.asDigit))
  def from(pixels: Seq[Int]): SpaceImage = SpaceImage(
    pixels.sliding(LayerSize, LayerSize).zipWithIndex.map { case (layerPixels, index) =>
      LayerData(index, layerPixels.sliding(width, width).map(LineData.apply) )
    }
  )
}
