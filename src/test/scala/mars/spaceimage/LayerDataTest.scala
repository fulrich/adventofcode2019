package mars.spaceimage

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class LayerDataTest extends AnyFunSuite with Matchers {
  test("Can count the occurrences of a digit across all lines in a layer") {
    val layer = LayerData.fromLines(0,
      LineData.pixels(1, 0, 2, 0, 3, 0),
      LineData.pixels(4, 0, 5, 0, 6, 0)
    )

    layer.numberOfDigits(0) shouldBe 6
    layer.numberOfDigits(4) shouldBe 1
  }
}
