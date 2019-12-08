package ship.computer.internals.instructions.io

import java.io.ByteArrayOutputStream

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.{ComputerTesting, IntcodeProgram}


class OutputTest extends AnyFunSuite with Matchers with ComputerTesting {
  test("Console outputs the output value to the standard console out") {
    val program = IntcodeProgram.load(4, 2, 99)
    val expected = IntcodeProgram.load(4, 2, 99).setInstructionPointer(2)
    val outputCapture = new ByteArrayOutputStream

    Console.withOut(outputCapture) {
      Output.Console.execute(program) shouldBe expected
    }

    outputCapture.toString.trim shouldBe "99"
  }

  test("Output Collection adds the output to the next parameters and tracks the output") {
    val program = IntcodeProgram.load(4, 2, 99)
    val expected = IntcodeProgram.load(4, 2, 99).setInstructionPointer(2)

    val output = Output.Collection()
    output.execute(program) shouldBe expected

    output.trackedOutput.last shouldBe 99
  }
}
