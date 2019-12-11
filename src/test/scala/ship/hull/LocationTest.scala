package ship.hull

import grids.{Direction, Point}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class LocationTest extends AnyFunSuite with Matchers {
  def originLocation(direction: Direction = Direction.Up): Location = Location(Point.Origin, direction)

  test("Can turn in a direction given a turn command") {
    originLocation().turn(0) shouldBe Location(Point.Origin, Direction.Left)
    originLocation().turn(1) shouldBe Location(Point.Origin, Direction.Right)
  }

  test("Can move in a direction based upon where the location is facing") {
    originLocation(Direction.Up).move shouldBe Location(Point(0, -1), Direction.Up)
    originLocation(Direction.Down).move shouldBe Location(Point(0, 1), Direction.Down)
    originLocation(Direction.Left).move shouldBe Location(Point(-1, 0), Direction.Left)
    originLocation(Direction.Right).move shouldBe Location(Point(1, 0), Direction.Right)
  }
}
