package mars.spaceimage

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class LineDataTest extends AnyFunSuite with Matchers {

  test("Can count the number of a specific digit in the line") {
    val line = LineData.pixels(1, 0, 2, 0, 3, 0, 4, 0)

    line.numberOfDigits(0) shouldBe 4
    line.numberOfDigits(2) shouldBe 1
  }
}
