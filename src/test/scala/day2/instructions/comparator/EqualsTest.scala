package day2.instructions.comparator

import day2.IntcodeState
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class EqualsTest extends AnyFunSuite with Matchers {
  test("Sets the third parameter to 1 if the first parameter is equal to the second") {
    val program = IntcodeState(Vector(Equals.Opcode, 5, 6, 7, 99, 2, 2, -1))
    val expected = IntcodeState(Vector(Equals.Opcode, 5, 6, 7, 99, 2, 2, 1), instructionPointer = 4)

    Equals.execute(program) shouldBe expected
  }

  test("Sets the third parameter to 0 if the first parameter is greater than the second") {
    val program = IntcodeState(Vector(Equals.Opcode, 5, 6, 7, 99, 2, 1, -1))
    val expected = IntcodeState(Vector(Equals.Opcode, 5, 6, 7, 99, 2, 1, 0), instructionPointer = 4)

    Equals.execute(program) shouldBe expected
  }

  test("Sets the third parameter to 0 if the first parameter is less than the second") {
    val program = IntcodeState(Vector(Equals.Opcode, 5, 6, 7, 99, 1, 2, -1))
    val expected = IntcodeState(Vector(Equals.Opcode, 5, 6, 7, 99, 1, 2, 0), instructionPointer = 4)

    Equals.execute(program) shouldBe expected
  }
}
