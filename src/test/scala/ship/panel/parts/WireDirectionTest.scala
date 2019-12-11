package ship.panel.parts

import grids.Point
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class WireDirectionTest extends AnyFunSuite with Matchers {
  test("Can handle parsing all wire directions") {
    WireDirection("U1") shouldBe WireDirection.Up(1)
    WireDirection("D1") shouldBe WireDirection.Down(1)
    WireDirection("L1") shouldBe WireDirection.Left(1)
    WireDirection("R1") shouldBe WireDirection.Right(1)
  }

  test("Can handle multiple lengths of directions") {
    WireDirection("U1") shouldBe WireDirection.Up(1)
    WireDirection("U11") shouldBe WireDirection.Up(11)
    WireDirection("U111") shouldBe WireDirection.Up(111)
    WireDirection("U1111") shouldBe WireDirection.Up(1111)
  }

  test("Can create a Line from a WireDirection and a Point") {
    val origin = Point(0, 0)

    WireDirection.Up(10).lineFrom(origin) shouldBe Line(origin, Point(0, 10))
    WireDirection.Down(10).lineFrom(origin) shouldBe Line(origin, Point(0, -10))
    WireDirection.Right(10).lineFrom(origin) shouldBe Line(origin, Point(10, 0))
    WireDirection.Left(10).lineFrom(origin) shouldBe Line(origin, Point(-10, 0))
  }
}
