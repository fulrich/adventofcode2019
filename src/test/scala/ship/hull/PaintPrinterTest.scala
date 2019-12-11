package ship.hull

import grids.Point
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class PaintPrinterTest extends AnyFunSuite with Matchers {

  test("Can create an output string for a row of painted points") {
    val points = Seq(PaintedPoint(Point(1, 0), Paint.White), PaintedPoint(Point(4, 0), Paint.White))
    val output = PaintPrinter.buildRowOutput(points)

    output shouldBe " #  #"
  }
}
