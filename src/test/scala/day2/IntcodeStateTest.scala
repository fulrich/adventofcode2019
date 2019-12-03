package day2

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class IntcodeStateTest extends AnyFunSuite with Matchers {
  test("Command will return the current command value") {
    val program = IntcodeState(Vector(1, 5, 6, 0, 99, 10, 20), instructionPointer = 2)

    program.instruction shouldBe Some(6)
  }

  test("Can move the instruction pointer using moveInstructionPointer") {
    val program = IntcodeState(Vector(1, 5, 6, 0, 99, 10, 20))

    program.moveInstructionPointer(4).instruction shouldBe Some(99)
  }

  test("Can get the parameter values") {
    val program = IntcodeState(Vector(1, 5, 6, 0, 99, 10, 20))
    val program2 = IntcodeState(Vector(1, 5, 6, 0, 99, 10, 20), 3)

    program.parameter(1) shouldBe 5
    program.parameter(2) shouldBe 6
    program.parameter(3) shouldBe 0

    program2.parameter(1) shouldBe 99
    program2.parameter(2) shouldBe 10
    program2.parameter(3) shouldBe 20
  }

  test("Can get a specific address value") {
    val program = IntcodeState(Vector(1, 5, 6, 0, 99, 10, 20))

    program.address(4) shouldBe 99
    program.address(5) shouldBe 10
    program.address(6) shouldBe 20
  }

  test("Can set a value in the intcode program") {
    val program = IntcodeState(Vector(1, 5, 6, 0, 99, 10, 20))
    val updatedProgram = program.set(3, 100);

    updatedProgram.memory shouldBe Vector(1, 5, 6, 100, 99, 10, 20)
  }
}