package ship.computer.internals.instructions.arithmetic

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.IntcodeState


class AdditionTest extends AnyFunSuite with Matchers {
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState(1, 0, 0, 0, 99)
    val output = IntcodeState(Vector(2, 0, 0, 0, 99), 4)

    Addition.execute(program) shouldBe output
  }
}
