package mercury

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class OrbitMapTest extends AnyFunSuite with Matchers {
  val testMap = Seq(
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
    "K)L"
  )

  test("Test map can be parsed") {
    val result = OrbitalMapBuilder.build(testMap)
    result.orbits shouldBe 42
  }

  test("Can find the path to a specific code") {
    val map = OrbitalMapBuilder.build(testMap)
    map.pathTo("C") shouldBe Vector("COM", "B", "C")
    map.pathTo("L") shouldBe Vector("COM", "B", "C", "D", "E", "J", "K", "L")
  }
}
