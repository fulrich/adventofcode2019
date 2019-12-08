package mars.spaceimage

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class LineDataTest extends AnyFunSuite with Matchers {
  test("Can count the number of a specific digit in the line") {
    val line = LineData.pixels(1, 0, 2, 0, 3, 0, 4, 0)

    line.numberOfDigits(0) shouldBe 4
    line.numberOfDigits(2) shouldBe 1
  }

  test("Can overlay the lines to create a new line") {
    val topLine = LineData.pixels(0, 2)
    val bottomLine = LineData.pixels(1, 1)

    topLine.overlay(bottomLine) shouldBe LineData.pixels(0, 1)
  }

  test("Can overlay multiple lines to create a valid final line") {
    val firstLine = LineData.pixels(0, 2)
    val secondLine = LineData.pixels(1, 1)
    val thirdLine = LineData.pixels(2, 2)
    val lastLine = LineData.pixels(0, 0)

    val overlayLine = firstLine
      .overlay(secondLine)
      .overlay(thirdLine)
      .overlay(lastLine)

    overlayLine shouldBe LineData.pixels(0, 1)
  }
}
