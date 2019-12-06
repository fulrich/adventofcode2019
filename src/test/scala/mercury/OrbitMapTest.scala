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
    val result = OrbitalMap.build(testMap)
    result.orbits shouldBe 42
  }

}
