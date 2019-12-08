package ship.computer.internals.instructions.io

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.instructions.io.OutputSource.ListOutputSource
import ship.computer.{ComputerTesting, IntcodeProgram}


class OutputTest extends AnyFunSuite with Matchers with ComputerTesting {
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeProgram.load(4, 2, 99)
    val expected = IntcodeProgram.load(4, 2, 99).setInstructionPointer(2)

    val output = ListOutputSource()
    Output(output).execute(program) shouldBe expected

    output.output().last shouldBe 99
  }
}
