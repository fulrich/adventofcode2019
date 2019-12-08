package ship.computer.internals.instructions.io

import java.io.StringReader

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.{ComputerTesting, IntcodeProgram}


class InputTest extends AnyFunSuite with Matchers with ComputerTesting{
  test("Console fetches the input from the standard console input") {
    val input = new StringReader("55")
    val program = IntcodeProgram.load(3, 0, 99)
    val expected = IntcodeProgram.load(55, 0, 99).setInstructionPointer(2)

    Console.withIn(input) {
      Input.Console.execute(program) shouldBe expected
    }
  }

  test("Static input provides input from an array and stores it to the first parameter address") {
    val program = IntcodeProgram.load(3, 0, 99)
    val expected = IntcodeProgram.load(55, 0, 99).setInstructionPointer(2)

    Input.Static(Vector(55)).execute(program) shouldBe expected
  }

  test("If the current Input strategy is NeedInput set the program to waiting") {
    val program = IntcodeProgram.load(3, 0, 99)
    val expected = program.state(state => state.copy(isWaiting = true))

    Input.NeedInput.execute(program) shouldBe expected
  }

  test("If the Input Strategy is Next Input it will provide the next input and switch to Need Input") {
    val program = IntcodeProgram.load(3, 0, 99)
    val expected = IntcodeProgram.load(55, 0, 99).setInstructionPointer(2).setInput(Input.NeedInput)

    Input.NextInput(55).execute(program) shouldBe expected
  }
}
