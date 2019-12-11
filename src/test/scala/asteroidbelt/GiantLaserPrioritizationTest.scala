package asteroidbelt

import asteroidbelt.targeting.GiantLaserPrioritization
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class GiantLaserPrioritizationTest extends AnyFunSuite with Matchers {
  test("Can determine the prioritization order of the giant laser") {
    val largeMap: AsteroidMap = AsteroidMap.fromLines(Vector(
      ".#..##.###...#######",
      "##.############..##.",
      ".#.######.########.#",
      ".###.#######.####.#.",
      "#####.##.#.##.###.##",
      "..#####..#.#########",
      "####################",
      "#.####....###.#.#.##",
      "##.#################",
      "#####.##.###..####..",
      "..######..##.#######",
      "####.##.####...##..#",
      ".#####..#.######.###",
      "##...#.##########...",
      "#.##########.#######",
      ".####.#.###.###.#.##",
      "....##.##.###..#####",
      ".#.#.###########.###",
      "#.#.#.#####.####.###",
      "###.##.####.##.#..##"
    ))

    val station = largeMap.asteroidWithMostVisibleAsteroids
    val laserPrioritization = new GiantLaserPrioritization(station)
    val prioritizedTargets = laserPrioritization.prioritize(largeMap)

    prioritizedTargets.target(1).asteroid shouldBe Asteroid(11, 12)
    prioritizedTargets.target(2).asteroid shouldBe Asteroid(12, 1)
    prioritizedTargets.target(3).asteroid shouldBe Asteroid(12, 2)
    prioritizedTargets.target(10).asteroid shouldBe Asteroid(12, 8)
    prioritizedTargets.target(20).asteroid shouldBe Asteroid(16, 0)
    prioritizedTargets.target(50).asteroid shouldBe Asteroid(16, 9)
    prioritizedTargets.target(100).asteroid shouldBe Asteroid(10, 16)
    prioritizedTargets.target(199).asteroid shouldBe Asteroid(9, 6)
    prioritizedTargets.target(200).asteroid shouldBe Asteroid(8, 2)
    prioritizedTargets.target(201).asteroid shouldBe Asteroid(10, 9)
    prioritizedTargets.target(299).asteroid shouldBe Asteroid(11, 1)
  }
}
