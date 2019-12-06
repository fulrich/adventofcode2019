package mercury

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class OrbitalPathFinderTest extends AnyFunSuite with Matchers {
  val map: SpaceObject = OrbitalMapBuilder.build(Vector(
    "COM)B",
    "B)C",
    "C)D",
    "D)E",
    "E)F",
    "B)G",
    "G)H",
    "D)I",
    "E)J",
    "J)K",
    "K)L",
    "K)YOU",
    "I)SAN"
  ))

  test("Can find a path from YOU (myself) to SAN (Santa)") {
    OrbitalPathFinder.between(map)("YOU", "SAN") shouldBe Vector("YOU", "K", "J", "E", "D", "I", "SAN")
  }

  test("Can determine the minimum number of transfers to reach Santa") {
    OrbitalPathFinder.minimumTransfersBetween(map)("SAN", "YOU") shouldBe 4
  }
}
