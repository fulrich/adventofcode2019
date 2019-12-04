package day3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class StepsClosestToTest extends AnyFunSuite with Matchers {
  test("End to End Example 1") {
    val wire1 = Wire.fromRaw("R8,U5,L5,D3")
    val wire2 = Wire.fromRaw("U7,R6,D4,L4")

    StepsClosestTo(Point.CentralPort)(wire1, wire2) shouldBe 30
  }

  test("End to End Example 2") {
    val wire1 = Wire.fromRaw("R75,D30,R83,U83,L12,D49,R71,U7,L72")
    val wire2 = Wire.fromRaw("U62,R66,U55,R34,D71,R55,D58,R83")

    StepsClosestTo(Point.CentralPort)(wire1, wire2) shouldBe 610
  }

  test("End to End Example 3") {
    val wire1 = Wire.fromRaw("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
    val wire2 = Wire.fromRaw("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

    StepsClosestTo(Point.CentralPort)(wire1, wire2) shouldBe 410
  }
}
