package ship.panel

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class IntersectionClosestToTest extends AnyFunSuite with Matchers {
  test("End to End Example 1") {
    val wire1 = Wire.fromRaw("R75,D30,R83,U83,L12,D49,R71,U7,L72")
    val wire2 = Wire.fromRaw("U62,R66,U55,R34,D71,R55,D58,R83")

    val closestIntersectionPoint = IntersectionClosestTo(Point.CentralPort)(wire1, wire2)
    closestIntersectionPoint.manhattanDistanceTo(Point.CentralPort) shouldBe 159
  }

  test("End to End Example 2") {
    val wire1 = Wire.fromRaw("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
    val wire2 = Wire.fromRaw("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

    val closestIntersectionPoint = IntersectionClosestTo(Point.CentralPort)(wire1, wire2)
    closestIntersectionPoint.manhattanDistanceTo(Point.CentralPort) shouldBe 135
  }
}
