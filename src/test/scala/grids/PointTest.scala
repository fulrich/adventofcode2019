package grids

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class PointTest extends AnyFunSuite with Matchers {
  test("Can determine the manhattan distance between points") {
    Point(3, 3).distanceTo(Point.Origin) shouldBe 6
    Point(-3, -3).distanceTo(Point.Origin) shouldBe 6

    Point.Origin.distanceTo(Point(3, 3)) shouldBe 6
    Point.Origin.distanceTo(Point(-3, -3)) shouldBe 6
  }

  test("Can move the point up") {
    Point.Origin.up(10) shouldBe Point(0, 10)
  }

  test("Can move the point down") {
    Point.Origin.down(10) shouldBe Point(0, -10)
  }

  test("Can move the point left") {
    Point.Origin.left(10) shouldBe Point(-10, 0)
  }

  test("Can move the point right") {
    Point.Origin.right(10) shouldBe Point(10, 0)
  }
}
