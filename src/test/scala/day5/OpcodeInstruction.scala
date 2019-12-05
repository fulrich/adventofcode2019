package day5

import day2.OpcodeInstruction
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class OpcodeInstruction extends AnyFunSuite with Matchers {
  test("Should be able to correctly determine the Opcode") {
    OpcodeInstruction(1).opcode shouldBe 1
    OpcodeInstruction(12).opcode shouldBe 12
    OpcodeInstruction(102).opcode shouldBe 2
    OpcodeInstruction(12345).opcode shouldBe 45
  }

  test("Should be able to get the paramter mode code for any parameter spot") {
    OpcodeInstruction(1).parameterMode(1) shouldBe 0
    OpcodeInstruction(1).parameterMode(2) shouldBe 0
    OpcodeInstruction(1).parameterMode(3) shouldBe 0

    OpcodeInstruction(12345).parameterMode(1) shouldBe 3
    OpcodeInstruction(12345).parameterMode(2) shouldBe 2
    OpcodeInstruction(12345).parameterMode(3) shouldBe 1
  }
}
