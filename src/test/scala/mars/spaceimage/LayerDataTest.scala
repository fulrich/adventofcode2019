package mars.spaceimage

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class LayerDataTest extends AnyFunSuite with Matchers {
  test("Can count the occurrences of a digit across all lines in a layer") {
    val layer = LayerData.fromLines(
      LineData.pixels(1, 0, 2, 0, 3, 0),
      LineData.pixels(4, 0, 5, 0, 6, 0)
    )

    layer.numberOfDigits(0) shouldBe 6
    layer.numberOfDigits(4) shouldBe 1
  }

  test("Can overlay layers over top of each other") {
    val layer1 = LayerData.fromLines(LineData.pixels(0, 2), LineData.pixels(2, 2))
    val layer2 = LayerData.fromLines(LineData.pixels(1, 1), LineData.pixels(2, 2))
    val layer3 = LayerData.fromLines(LineData.pixels(2, 2), LineData.pixels(1, 2))
    val layer4 = LayerData.fromLines(LineData.pixels(0, 0), LineData.pixels(0, 0))

    val expected = LayerData.fromLines(LineData.pixels(0, 1), LineData.pixels(1, 0))
    val actual = layer1.overlay(layer2).overlay(layer3).overlay(layer4)

    actual shouldBe expected
  }
}
