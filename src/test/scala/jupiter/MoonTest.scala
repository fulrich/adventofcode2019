package jupiter

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class MoonTest extends AnyFunSuite with Matchers {
  test("Can correctly apply gravity of another moon to update velocity") {
    val moon1 = Moon.at(3, 5, 20)
    val otherMoon = Moon.at(5, 3, 50)

    moon1.applyGravity(otherMoon) shouldBe Moon(moon1.position, Position3d(1, -1, 1))
    otherMoon.applyGravity(moon1) shouldBe Moon(otherMoon.position, Position3d(-1, 1, -1))
  }

  test("Can correctly apply a Moons velocity to itself to move it") {
    val moon = Moon(position = Position3d(5, 5, 5), velocity = Position3d(-1, 1, 5))

    moon.applyVelocity shouldBe moon.copy(position = Position3d(4, 6, 10))
  }

  test("Can calculate the potential, kinetic, and total energy of a moon") {
    val moon = Moon(position = Position3d(x = 1, y = -8, z= 0), velocity = Position3d(x = -1, y= 1, z= 3))

    moon.potentialEnergy shouldBe 9
    moon.kineticEnergy shouldBe 5
    moon.totalEnergy shouldBe 45
  }
}
