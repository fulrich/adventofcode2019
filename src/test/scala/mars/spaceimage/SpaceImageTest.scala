package mars.spaceimage

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SpaceImageTest extends AnyFunSuite with Matchers {

  test("Can find the layer with the least number of a digit") {
    val layer1 = LayerData.fromLines(0, LineData.pixels(1, 2, 3), LineData.pixels(4, 5, 6))
    val layer2 = LayerData.fromLines(1, LineData.pixels(7, 8, 9), LineData.pixels(0, 1, 2))
    val spaceImage = SpaceImage.fromLayers(layer1, layer2)

    spaceImage.layerWithFewest(0).number shouldBe 0
    spaceImage.layerWithFewest(3).number shouldBe 1
  }

}
