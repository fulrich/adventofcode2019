package ship.computer.internals

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class InstructionTest extends AnyFunSuite with Matchers {
  test("Should be able to correctly determine the Opcode") {
    Instruction(1).opcode shouldBe 1
    Instruction(12).opcode shouldBe 12
    Instruction(102).opcode shouldBe 2
    Instruction(12345).opcode shouldBe 45
  }

  test("Should be able to get the paramter mode code for any parameter spot") {
    Instruction(1).parameterMode(1) shouldBe 0
    Instruction(1).parameterMode(2) shouldBe 0
    Instruction(1).parameterMode(3) shouldBe 0

    Instruction(12345).parameterMode(1) shouldBe 3
    Instruction(12345).parameterMode(2) shouldBe 2
    Instruction(12345).parameterMode(3) shouldBe 1
  }
}
