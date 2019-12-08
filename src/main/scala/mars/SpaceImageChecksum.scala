package mars

import mars.spaceimage.SpaceImage

object SpaceImageChecksum {
  def apply(spaceImage: SpaceImage): Int = {
    val minimumLayer = spaceImage.layerWithFewest(0)

    minimumLayer.numberOfDigits(1) * minimumLayer.numberOfDigits(2)
  }
}
