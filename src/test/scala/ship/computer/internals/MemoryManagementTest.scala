package ship.computer.internals

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram


class MemoryManagementTest extends AnyFunSuite with Matchers {
  test("Opcode will return the current command value") {
    val program = IntcodeProgram(Vector(1, 5, 6, 0, 99, 10, 20), instructionPointer = 2)

    program.opcode shouldBe 6
  }

  test("Can move the instruction pointer using moveInstructionPointer") {
    val program = IntcodeProgram(Vector(1, 5, 6, 0, 99, 10, 20))

    program.moveInstructionPointer(4).opcode shouldBe 99
  }

//  test("Can get the parameter values") {
//    val program = IntcodeProgram(Vector(1, 5, 6, 0, 99, 10, 20))
//
//    program.parameter(1) shouldBe Parameter(address = 5, value = 10)
//    program.parameter(2) shouldBe Parameter(address = 6, value = 20)
//    program.parameter(3) shouldBe Parameter(address = 0, value = 1)
//  }

  test("Can get a specific address value") {
    val program = IntcodeProgram(Vector(1, 5, 6, 0, 99, 10, 20))

    program.address(4) shouldBe 99
    program.address(5) shouldBe 10
    program.address(6) shouldBe 20
  }

  test("Can set a value in the intcode program") {
    val program = IntcodeProgram(Vector(1, 5, 6, 0, 99, 10, 20))
    val updatedProgram = program.set(Parameter.at(program, 3), 100);

    updatedProgram.memory shouldBe Vector(1, 5, 6, 100, 99, 10, 20)
  }
}
