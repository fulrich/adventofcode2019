package day2.instructions.control

import day2.IntcodeState
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class JumpIfFalseTest extends AnyFunSuite with Matchers {
  test("If the value is 0 jump to the given address") {
    val program = IntcodeState.initial(5, 4, 3, 99, 0)
    val expected = program.setInstructionPointer(99)

    JumpIfFalse.execute(program) shouldBe expected
  }

  test("If the value is not 0 do not jump and instead do a standard increment") {
    val program = IntcodeState.initial(5, 4, 3, 99, -5)
    val expected = program.moveInstructionPointer(JumpIfTrue.NumberOfParameters + 1)

    JumpIfFalse.execute(program) shouldBe expected
  }
}
