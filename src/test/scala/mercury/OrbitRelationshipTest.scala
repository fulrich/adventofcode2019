package mercury

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class OrbitRelationshipTest extends AnyFunSuite with Matchers {
  test("Can parse out an orbit relationship string into an orbit relationship") {
    OrbitRelationship.parse("A)B") shouldBe OrbitRelationship("A", "B")
    OrbitRelationship.parse("AA)BB") shouldBe OrbitRelationship("AA", "BB")
    OrbitRelationship.parse("AAA)BBB") shouldBe OrbitRelationship("AAA", "BBB")
  }
}
