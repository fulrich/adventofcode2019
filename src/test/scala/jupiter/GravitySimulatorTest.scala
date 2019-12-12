package jupiter

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class GravitySimulatorTest extends AnyFunSuite with Matchers {
  val jupiterTest: JupiterOrbit = JupiterOrbit(Vector(
    Moon.at(x = -1, y = 0, z = 2),
    Moon.at(x = 2, y = -10, z = -7),
    Moon.at(x = 4, y = -8, z= 8),
    Moon.at(x = 3, y = 5, z = -1),
  ))

  test("Can simulate a single step of gravity") {
    val result = GravitySimulator.step(jupiterTest, 1)

    result.moons should contain theSameElementsAs Vector(
      Moon(position = Position3d(x = 2, y = -1, z= 1), velocity = Position3d(x = 3, y = -1, z = -1)),
      Moon(position = Position3d(x = 3, y = -7, z = -4), velocity = Position3d(x = 1, y= 3, z= 3)),
      Moon(position = Position3d(x = 1, y = -7, z= 5), velocity = Position3d(x = -3, y= 1, z = -3)),
      Moon(position = Position3d(x = 2, y= 2, z= 0), velocity = Position3d(x = -1, y = -3, z= 1))
    )
  }

  test("Can simulate 2 steps of gravity") {
    val result = GravitySimulator.step(jupiterTest, 2)

    result.moons should contain theSameElementsAs Vector(
      Moon(position = Position3d(x = 5, y = -3, z = -1), velocity = Position3d(x = 3, y = -2, z = -2)),
      Moon(position = Position3d(x = 1, y = -2, z= 2), velocity = Position3d(x = -2, y= 5, z= 6)),
      Moon(position = Position3d(x = 1, y = -4, z = -1), velocity = Position3d(x = 0, y= 3, z = -6)),
      Moon(position = Position3d(x = 1, y = -4, z= 2), velocity = Position3d(x = -1, y = -6, z= 2))
    )
  }

  test("Can simulate 3 steps of gravity") {
    val result = GravitySimulator.step(jupiterTest, 3)

    result.moons should contain theSameElementsAs Vector(
      Moon(position = Position3d(x = 5, y = -6, z = -1), velocity = Position3d(x = 0, y = -3, z= 0)),
      Moon(position = Position3d(x = 0, y= 0, z= 6), velocity = Position3d(x = -1, y= 2, z= 4)),
      Moon(position = Position3d(x = 2, y= 1, z = -5), velocity = Position3d(x = 1, y= 5, z = -4)),
      Moon(position = Position3d(x = 1, y = -8, z= 2), velocity = Position3d(x = 0, y = -4, z= 0))
    )
  }

  test("Can simulate 4 steps of gravity") {
    val result = GravitySimulator.step(jupiterTest, 4)

    result.moons should contain theSameElementsAs Vector(
      Moon(position = Position3d(x = 2, y = -8, z= 0), velocity = Position3d(x = -3, y = -2, z= 1)),
      Moon(position = Position3d(x = 2, y= 1, z= 7), velocity = Position3d(x = 2, y= 1, z= 1)),
      Moon(position = Position3d(x = 2, y= 3, z = -6), velocity = Position3d(x = 0, y= 2, z = -1)),
      Moon(position = Position3d(x = 2, y = -9, z= 1), velocity = Position3d(x = 1, y = -1, z = -1))
    )
  }

  test("Can simulate 5 steps of gravity") {
    val result = GravitySimulator.step(jupiterTest, 5)

    result.moons should contain theSameElementsAs Vector(
      Moon(position = Position3d(x = -1, y = -9, z= 2), velocity = Position3d(x = -3, y = -1, z= 2)),
      Moon(position = Position3d(x = 4, y= 1, z= 5), velocity = Position3d(x = 2, y= 0, z = -2)),
      Moon(position = Position3d(x = 2, y= 2, z = -4), velocity = Position3d(x = 0, y = -1, z= 2)),
      Moon(position = Position3d(x = 3, y = -7, z = -1), velocity = Position3d(x = 1, y= 2, z = -2))
    )
  }

  test("Can simulate 10 steps of gravity") {
    val result = GravitySimulator.step(jupiterTest, 10)

    result.moons should contain theSameElementsAs Vector(
      Moon(position = Position3d(x = 2, y= 1, z = -3), velocity = Position3d(x = -3, y = -2, z= 1)),
      Moon(position = Position3d(x = 1, y = -8, z= 0), velocity = Position3d(x = -1, y= 1, z= 3)),
      Moon(position = Position3d(x = 3, y = -6, z= 1), velocity = Position3d(x = 3, y= 2, z = -3)),
      Moon(position = Position3d(x = 2, y= 0, z= 4), velocity = Position3d(x = 1, y = -1, z = -1))
    )
  }

  test("Can determine how long it takes to return to the initial state") {
    EfficientGravitySimulator.stepsToInitial(jupiterTest) shouldBe 2772
  }

  test("Can calculate the number of steps even when it is a very large number") {
    val longRunning = JupiterOrbit(Vector(
      Moon.at(x = -8, y = -10, z = 0),
      Moon.at(x = 5, y = 5, z = 10),
      Moon.at(x = 2, y = -7, z = 3),
      Moon.at(x = 9, y = -8, z = -3),
    ))

    EfficientGravitySimulator.stepsToInitial(longRunning) shouldBe 4686774924L
  }
}
