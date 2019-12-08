package ship.computer.internals

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram


class InstructionControlTest extends AnyFunSuite with Matchers {
  test("Should be able to correctly determine the Opcode") {
    println(IntcodeProgram.load(1))
    IntcodeProgram.load(1).opcode shouldBe 1
    IntcodeProgram.load(12).opcode shouldBe 12
    IntcodeProgram.load(102).opcode shouldBe 2
    IntcodeProgram.load(12345).opcode shouldBe 45
  }

  test("Should be able to get the paramter mode code for any parameter spot") {
    IntcodeProgram.load(1).parameterMode(1) shouldBe 0
    IntcodeProgram.load(1).parameterMode(2) shouldBe 0
    IntcodeProgram.load(1).parameterMode(3) shouldBe 0

    IntcodeProgram.load(12345).parameterMode(1) shouldBe 3
    IntcodeProgram.load(12345).parameterMode(2) shouldBe 2
    IntcodeProgram.load(12345).parameterMode(3) shouldBe 1
  }
}
