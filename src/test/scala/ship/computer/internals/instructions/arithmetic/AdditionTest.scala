package ship.computer.internals.instructions.arithmetic

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram


class AdditionTest extends AnyFunSuite with Matchers {
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeProgram.load(Addition.Opcode, 0, 0, 0, 99)
    val output = IntcodeProgram.load(2, 0, 0, 0, 99).setInstructionPointer(4)

    Addition.execute(program) shouldBe output
  }
}
