package ship.computer.internals.instructions.io

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.instructions.io.InputSource.ListInputSource
import ship.computer.{ComputerTesting, IntcodeProgram}


class InputTest extends AnyFunSuite with Matchers with ComputerTesting{
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeProgram.load(3, 0, 99)
    val output = IntcodeProgram.load(55, 0, 99).setInstructionPointer(2)

    val inputSource = new ListInputSource(Vector(55))
    Input(inputSource).execute(program) shouldBe output
  }
}
