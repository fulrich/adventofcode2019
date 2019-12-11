package grids

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class DirectionTest extends AnyFunSuite with Matchers {
  test("Can turn left in a full circle") {
    val faceUp = Direction.Up
    val firstLeft = faceUp.turnLeft
    val secondLeft = firstLeft.turnLeft
    val thirdLeft = secondLeft.turnLeft
    val fourthLeft = thirdLeft.turnLeft

    firstLeft shouldBe Direction.Left
    secondLeft shouldBe Direction.Down
    thirdLeft shouldBe Direction.Right
    fourthLeft shouldBe Direction.Up
  }

  test("Can turn right in a circle") {
    val faceUp = Direction.Up
    val firstRight = faceUp.turnRight
    val secondRight = firstRight.turnRight
    val thirdRight = secondRight.turnRight
    val fourthRight = thirdRight.turnRight

    firstRight shouldBe Direction.Right
    secondRight shouldBe Direction.Down
    thirdRight shouldBe Direction.Left
    fourthRight shouldBe Direction.Up
  }

  test("Up can move a point up a number of steps"){
    Direction.Up.move(Point.Origin, 1) shouldBe Point(0, -1)
    Direction.Up.move(Point.Origin, 5) shouldBe Point(0, -5)
  }

  test("Down can move a point down a number of steps"){
    Direction.Down.move(Point.Origin, 1) shouldBe Point(0, 1)
    Direction.Down.move(Point.Origin, 5) shouldBe Point(0, 5)
  }

  test("Left can move a point left a number of steps"){
    Direction.Left.move(Point.Origin, 1) shouldBe Point(-1, 0)
    Direction.Left.move(Point.Origin, 5) shouldBe Point(-5, 0)
  }

  test("Right can move a point right a number of steps"){
    Direction.Right.move(Point.Origin, 1) shouldBe Point(1, 0)
    Direction.Right.move(Point.Origin, 5) shouldBe Point(5, 0)
  }
}
