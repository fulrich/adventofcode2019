package mars

import mars.spaceimage.{LayerData, LineData}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class SpaceImageParserTest extends AnyFunSuite with Matchers {
  test("Can parse a simple space image into layers") {
    val parser = SpaceImageParser(width = 3, height = 2)
    val spaceImage = parser.from("123456789012468135")

    spaceImage.layer(0) shouldBe LayerData.fromLines(0, LineData.pixels(1, 2, 3), LineData.pixels(4, 5, 6))
    spaceImage.layer(1) shouldBe LayerData.fromLines(1, LineData.pixels(7, 8, 9), LineData.pixels(0, 1, 2))
    spaceImage.layer(2) shouldBe LayerData.fromLines(2, LineData.pixels(4, 6, 8), LineData.pixels(1, 3, 5))
  }
}
