package ship.computer.internals.instructions.control

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram


class AdjustRelativeBaseTest extends AnyFunSuite with Matchers {
  test("Sets the relative pointer to the value given by the first parameter") {
    val program = IntcodeProgram.load(AdjustRelativeBase.Opcode, 2, 99)
    val expected = program.relativeBase(99).setInstructionPointer(2)

    AdjustRelativeBase.execute(program) shouldBe expected
  }

  test("Can adjust the relative pointer back down") {
    val program = IntcodeProgram.load(AdjustRelativeBase.Opcode, 3, 99, -7).relativeBase(50)
    val expected = program.relativeBase(43).setInstructionPointer(2)

    AdjustRelativeBase.execute(program) shouldBe expected
  }
}
