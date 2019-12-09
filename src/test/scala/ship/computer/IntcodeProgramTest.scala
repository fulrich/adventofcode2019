package ship.computer

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.Configuration
import ship.computer.internals.instructions.io.Input

class IntcodeProgramTest extends AnyFunSuite with Matchers with ComputerTesting {
  test("Can handle simple addition intcode programs") {
    val program = IntcodeProgram.load(1, 0, 0, 0, 99)
    val output = Vector(2, 0, 0, 0, 99)

    program.start().memory shouldBe output
  }

  test("Can handle simple multiplication intcode programs") {
    val program = IntcodeProgram.load(2, 3, 0, 3, 99)
    val output = Vector(2, 3, 0, 6, 99)

    program.start().memory shouldBe output
  }

  test("Can handle complex multiplication intcode programs") {
    val program = IntcodeProgram.load(2, 4, 4, 5, 99, 0)
    val output = Vector(2, 4, 4, 5, 99, 9801)

    program.start().memory shouldBe output
  }

  test("Can handle complex intcode programs") {
    val program = IntcodeProgram.load(1, 1, 1, 4, 99, 5, 6, 0, 99)
    val output = Vector(30, 1, 1, 4, 2, 5, 6, 0, 99)

    program.start().memory shouldBe output
  }

  test("Can handle different modes of parameters") {
    val program = IntcodeProgram.load(1002, 4, 3, 4, 33)
    val output = Vector(1002, 4, 3, 4, 99)

    program.start().memory shouldBe output
  }

  test("Can run an input and output program") {
    val program = IntcodeProgram.load(3, 0, 4, 0, 99)
    val programCompleteState = Vector(250, 0, 4, 0, 99)

    program.testInput(250).testExecute { (state, output) =>
      state.memory shouldBe programCompleteState
      output.head shouldBe 250
    }
  }

  test("Can run a program that tests if the input is equal to 8 using position mode") {
    val isEqualTo8 = IntcodeProgram(Vector(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8))

    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(0).testOutput { output => output.last shouldBe 0 }
  }

  test("Can run a program that tests if the input is less than 8 using position mode") {
    val isEqualTo8 = IntcodeProgram(Vector(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8))

    isEqualTo8.testInput(6).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 0 }
    isEqualTo8.testInput(10).testOutput { output => output.last shouldBe 0 }
  }

  test("Can run a program that tests if the input is equal to 8 using immediate mode") {
    val isEqualTo8 = IntcodeProgram(Vector(3, 3, 1108, -1, 8, 3, 4, 3, 99))

    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(10).testOutput { output => output.last shouldBe 0 }
  }

  test("Can run a program that tests if the input is less than 8 using immediate mode") {
    val isEqualTo8 = IntcodeProgram(Vector(3, 3, 1107, -1, 8, 3, 4, 3, 99))

    isEqualTo8.testInput(6).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 0 }
    isEqualTo8.testInput(10).testOutput { output => output.last shouldBe 0 }
  }

  test("When running with single inputs will pause the execution while waiting for the next input") {
    val configuration = Configuration.singleInput
    val program = IntcodeProgram.load(3, 0, 99).configure(configuration)

    val pausedProgram = program.start()
    pausedProgram shouldBe program.startWaiting

    val continuedWithInput = pausedProgram.continue(55)
    continuedWithInput shouldBe IntcodeProgram(Vector(55, 0, 99), 2).completed.configure(Configuration.singleInput)
  }

  test("When a program needs two inputs it will wait twice") {
    val configuration = Configuration.singleInput(55)
    val program = IntcodeProgram.load(3, 0, 3, 2, 99).configure(configuration)

    val firstInputProvided = program.start()
    firstInputProvided shouldBe program.set(0, 55).startWaiting.setInstructionPointer(2).setInput(Input.NeedInput)

    val secondInputProvided = firstInputProvided.continue(200)
    val secondExpected = firstInputProvided.set(2, 200).completed.stopWaiting.setInput(Input.NeedInput).setInstructionPointer(4)
    secondInputProvided shouldBe secondExpected
  }

  test("Can track when an error occurrs") {
    val program = IntcodeProgram.load(20, 0, 3, 2, 99)
    val result = program.start()

    result.hasFault shouldBe true
    result.state.error should contain ("Unknown Opcode Instruction: 20")
  }
}
