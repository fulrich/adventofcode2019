package ship.computer.internals.instructions.comparator

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram


class EqualsTest extends AnyFunSuite with Matchers {
  test("Sets the third parameter to 1 if the first parameter is equal to the second") {
    val program = IntcodeProgram.load(Equals.Opcode, 5, 6, 7, 99, 2, 2, -1)
    val expected = IntcodeProgram.load(Equals.Opcode, 5, 6, 7, 99, 2, 2, 1).setInstructionPointer(4)

    Equals.execute(program) shouldBe expected
  }

  test("Sets the third parameter to 0 if the first parameter is greater than the second") {
    val program = IntcodeProgram.load(Equals.Opcode, 5, 6, 7, 99, 2, 1, -1)
    val expected = IntcodeProgram.load(Equals.Opcode, 5, 6, 7, 99, 2, 1, 0).setInstructionPointer(4)

    Equals.execute(program) shouldBe expected
  }

  test("Sets the third parameter to 0 if the first parameter is less than the second") {
    val program = IntcodeProgram.load(Equals.Opcode, 5, 6, 7, 99, 1, 2, -1)
    val expected = IntcodeProgram.load(Equals.Opcode, 5, 6, 7, 99, 1, 2, 0).setInstructionPointer(4)

    Equals.execute(program) shouldBe expected
  }
}
