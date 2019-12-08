package ship.computer.internals.instructions.control

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram

class HaltTest extends AnyFunSuite with Matchers {
  test("The Halt instruction should set the incode state to complete") {
    val program = IntcodeProgram.load(2, 3, 0, 3, 99)

    Halt.execute(program).isComplete shouldBe true
  }
}
