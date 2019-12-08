package mars.spaceimage

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class SpaceImageTest extends AnyFunSuite with Matchers {
  test("Can find the layer with the least number of a digit") {
    val layer1 = LayerData.fromLines(LineData.pixels(1, 2, 3), LineData.pixels(4, 5, 6))
    val layer2 = LayerData.fromLines(LineData.pixels(7, 8, 9), LineData.pixels(0, 1, 2))
    val spaceImage = SpaceImage.fromLayers(layer1, layer2)

    spaceImage.layerWithFewest(0) shouldBe layer1
    spaceImage.layerWithFewest(3) shouldBe layer2
  }

  test("Can find the overlay image of a SpaceImage ") {
    val spaceImage = SpaceImage.fromLayers(
      LayerData.fromLines(LineData.pixels(0, 2), LineData.pixels(2, 2)),
      LayerData.fromLines(LineData.pixels(1, 1), LineData.pixels(2, 2)),
      LayerData.fromLines(LineData.pixels(2, 2), LineData.pixels(1, 2)),
      LayerData.fromLines(LineData.pixels(0, 0), LineData.pixels(0, 0))
    )

    val expectedOverlay = LayerData.fromLines(LineData.pixels(0, 1), LineData.pixels(1, 0))
    spaceImage.overlay shouldBe expectedOverlay
  }
}
