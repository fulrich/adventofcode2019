package ship.panel.parts

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class PointTest extends AnyFunSuite with Matchers {
  test("Can determine the manhattan distance between points") {
    Point(3, 3).manhattanDistanceTo(Point.CentralPort) shouldBe 6
    Point(-3, -3).manhattanDistanceTo(Point.CentralPort) shouldBe 6

    Point.CentralPort.manhattanDistanceTo(Point(3, 3)) shouldBe 6
    Point.CentralPort.manhattanDistanceTo(Point(-3, -3)) shouldBe 6
  }

  test("Can move the point up") {
    Point.CentralPort.up(10) shouldBe Point(0, 10)
  }

  test("Can move the point down") {
    Point.CentralPort.down(10) shouldBe Point(0, -10)
  }

  test("Can move the point left") {
    Point.CentralPort.left(10) shouldBe Point(-10, 0)
  }

  test("Can move the point right") {
    Point.CentralPort.right(10) shouldBe Point(10, 0)
  }
}
