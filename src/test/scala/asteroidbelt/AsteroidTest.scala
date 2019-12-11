package asteroidbelt

import asteroidbelt.Quadrant._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class AsteroidTest extends AnyFunSuite with Matchers {

  test("Can determine the slope from one asteroid to another") {
    val origin = Asteroid(1, 1)

    Asteroid(2, 0).slopeFrom(origin) shouldBe Slope(-1, QuadrantOne)
    Asteroid(2, 2).slopeFrom(origin) shouldBe Slope(1, QuadrantTwo)
    Asteroid(0, 2).slopeFrom(origin) shouldBe Slope(-1, QuadrantThree)
    Asteroid(0, 0).slopeFrom(origin) shouldBe Slope(1, QuadrantFour)
  }

  test("Obscured asteroids have the same slope as the asteroid that is obscuring them") {
    val origin = Asteroid(0, 0)
    val visibleAsteroid = Asteroid(1, 1)
    val obscuredAsteroid = Asteroid(2, 2)

    origin.slopeFrom(visibleAsteroid) shouldBe origin.slopeFrom(obscuredAsteroid)
  }
}
