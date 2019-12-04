package day3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class WireTest extends AnyFunSuite with Matchers {
  val Expected: Wire = Wire.fromLines(
    Line(Point.CentralPort, Point(8, 0)),
    Line(Point(8, 0), Point(8, 5)),
    Line(Point(8, 5), Point(3, 5)),
    Line(Point(3, 5), Point(3, 2))
  )

  test("Can determine the number of steps to a specific point on the line") {
    val wire1 = Wire.fromRaw("R8,U5,L5,D3")
    val wire2 = Wire.fromRaw("U7,R6,D4,L4")

    wire1.stepsTo(Point(3, 3)) shouldBe 20
    wire2.stepsTo(Point(3, 3)) shouldBe 20

    wire1.stepsTo(Point(6, 5)) shouldBe 15
    wire2.stepsTo(Point(6, 5)) shouldBe 15
  }

  test("Can determine the intersection points of two wires") {
      val wire1 = Wire.fromRaw("R8,U5,L5,D3")
      val wire2 = Wire.fromRaw("U7,R6,D4,L4")

      wire1.intersectionPoints(wire2) should contain theSameElementsAs Vector(Point(3, 3), Point(6, 5))
  }

  test("Can determine all the points in a wire") {
    val directions = "R1,U1,L1,D1"
    val wire = Wire.fromRaw(directions)
    val expectedPoints = Vector(Point(0,0), Point(1, 0), Point(1, 1), Point(0,1), Point(0, 0))

    wire.allPoints shouldBe expectedPoints
  }

  test("Can construct a wire from a raw string of unparsed directions") {
    val directions = "R8,U5,L5,D3"
    Wire.fromRaw(directions) shouldBe Expected
  }

  test("Can construct a wire from a list of unparsed wire directions") {
    val directions = Vector("R8", "U5", "L5", "D3")
    Wire.fromRawDirections(directions) shouldBe Expected
  }

  test("Can construct a wire from a list of wire directions") {
    val directions = Vector(WireDirection.Right(8), WireDirection.Up(5), WireDirection.Left(5), WireDirection.Down(3))
    Wire.fromDirections(directions) shouldBe Expected
  }
}
