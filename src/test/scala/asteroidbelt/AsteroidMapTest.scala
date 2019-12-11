package asteroidbelt

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class AsteroidMapTest extends AnyFunSuite with Matchers {
  val SmallMap: AsteroidMap = AsteroidMap.fromLines(Vector(
    ".#..#",
    ".....",
    "#####",
    "....#",
    "...##",
  ))

  test("Can parse out a list of asteroids on the map") {
    SmallMap.asteroids should contain theSameElementsInOrderAs Vector(
      Asteroid(1,0),
      Asteroid(4,0),
      Asteroid(0,2),
      Asteroid(1,2),
      Asteroid(2,2),
      Asteroid(3,2),
      Asteroid(4,2),
      Asteroid(4,3),
      Asteroid(3,4),
      Asteroid(4,4)
    )
  }

  test("Can determine how many asteroids a given asteroid can see") {
    SmallMap.visibleAsteroidsFor(Asteroid(4, 0)) shouldBe 7
    SmallMap.visibleAsteroidsFor(Asteroid(0, 2)) shouldBe 6
    SmallMap.visibleAsteroidsFor(Asteroid(4, 2)) shouldBe 5
  }

  test("Can determine the asteroid that can view the most other asteroids") {
    val expected = Asteroid(3, 4)
    SmallMap.asteroidWithMostVisibleAsteroids shouldBe expected
    SmallMap.visibleAsteroidsFor(expected) shouldBe 8
  }

  test("Can determine the asteroid that can view the most other asteroids on map 2") {
    val map2: AsteroidMap = AsteroidMap.fromLines(Vector(
      "......#.#.",
      "#..#.#....",
      "..#######.",
      ".#.#.###..",
      ".#..#.....",
      "..#....#.#",
      "#..#....#.",
      ".##.#..###",
      "##...#..#.",
      ".#....####"
    ))

    val expected = Asteroid(5, 8)
    map2.asteroidWithMostVisibleAsteroids shouldBe expected
    map2.visibleAsteroidsFor(expected) shouldBe 33
  }

  test("Can determine the asteroid that can view the most other asteroids on map 3") {
    val map3: AsteroidMap = AsteroidMap.fromLines(Vector(
      "#.#...#.#.",
      ".###....#.",
      ".#....#...",
      "##.#.#.#.#",
      "....#.#.#.",
      ".##..###.#",
      "..#...##..",
      "..##....##",
      "......#...",
      ".####.###."
    ))

    val expected = Asteroid(1, 2)
    map3.asteroidWithMostVisibleAsteroids shouldBe expected
    map3.visibleAsteroidsFor(expected) shouldBe 35
  }

  test("Can determine the asteroid that can view the most other asteroids on map 4") {
    val map4: AsteroidMap = AsteroidMap.fromLines(Vector(
      ".#..#..###",
      "####.###.#",
      "....###.#.",
      "..###.##.#",
      "##.##.#.#.",
      "....###..#",
      "..#.#..#.#",
      "#..#.#.###",
      ".##...##.#",
      ".....#.#.."
    ))

    val expected = Asteroid(6, 3)
    map4.asteroidWithMostVisibleAsteroids shouldBe expected
    map4.visibleAsteroidsFor(expected) shouldBe 41
  }

  test("Can determine the asteroid that can view the most other asteroids on a large map") {
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

    val expected = Asteroid(11, 13)
    largeMap.asteroidWithMostVisibleAsteroids shouldBe expected
    largeMap.visibleAsteroidsFor(expected) shouldBe 210
  }
}
