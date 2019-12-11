package ship.panel.parts

import grids.Point
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class LineTest extends AnyFunSuite with Matchers {
  test("Can get all the points in a line") {
    val horizontalLine = Line(Point(1,0), Point(5, 0))
    val verticalLine = Line(Point(0,0), Point(0, 5))

    val expectedHorizontalPoints = Vector(Point(1, 0), Point(2, 0), Point(3, 0), Point(4, 0), Point(5, 0))
    val expectedVerticalPoints = Vector(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3), Point(0, 4), Point(0, 5))

    horizontalLine.allPoints shouldBe expectedHorizontalPoints
    verticalLine.allPoints shouldBe expectedVerticalPoints
  }

  test("Retains directionality when building the list of points") {
    val lineGoingLeft = Line(Point(5,0), Point(1, 0))
    val expectedPoints = Vector(Point(5, 0), Point(4, 0), Point(3, 0), Point(2, 0), Point(1, 0))

    lineGoingLeft.allPoints shouldBe expectedPoints
  }

  test("Can detect if the line is horizontal") {
    val horizontalLine = Line(Point(1,0), Point(5, 0))

    horizontalLine.isHorizontal shouldBe true
    horizontalLine.isVertical shouldBe false
  }

  test("Can detect if the line is vertical") {
    val verticalLine = Line(Point(0,0), Point(0, 5))

    verticalLine.isHorizontal shouldBe false
    verticalLine.isVertical shouldBe true
  }
}
