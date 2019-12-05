package ship.computer

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.IntcodeState

class IntcodeComputerTest extends AnyFunSuite with Matchers with ComputerTesting {
  test("Can handle simple addition intcode programs") {
    val program = IntcodeState(1, 0, 0, 0, 99)
    val output = Vector(2, 0, 0, 0, 99)

    IntcodeComputer(program).execute().memory shouldBe output
  }

  test("Can handle simple multiplication intcode programs") {
    val program = IntcodeState(2, 3, 0, 3, 99)
    val output = Vector(2, 3, 0, 6, 99)

    IntcodeComputer(program).execute().memory shouldBe output
  }

  test("Can handle complex multiplication intcode programs") {
    val program = IntcodeState(2, 4, 4, 5, 99, 0)
    val output = Vector(2, 4, 4, 5, 99, 9801)

    IntcodeComputer(program).execute().memory shouldBe output
  }

  test("Can handle complex intcode programs") {
    val program = IntcodeState(1, 1, 1, 4, 99, 5, 6, 0, 99)
    val output = Vector(30, 1, 1, 4, 2, 5, 6, 0, 99)

    IntcodeComputer(program).execute().memory shouldBe output
  }

  test("Can handle different modes of parameters") {
    val program = IntcodeState(1002, 4, 3, 4, 33)
    val output = Vector(1002, 4, 3, 4, 99)

    IntcodeComputer(program).execute().memory shouldBe output
  }

  test("Can run an input and output program") {
    val program = IntcodeComputer(IntcodeState(3, 0, 4, 0, 99))
    val programCompleteState = Vector(250, 0, 4, 0, 99)

    program.testInput(250).testExecute { (state, output) =>
      state.memory shouldBe programCompleteState
      output.head shouldBe 250
    }
//    val output = withIO("250") {
//      IntcodeComputer(program).execute().memory shouldBe programCompleteState
//    }
//
//    output.head shouldBe 250
  }

  test("Can run a program that tests if the input is equal to 8 using position mode") {
    val isEqualTo8 = IntcodeComputer(IntcodeState(Vector(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)))

    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(0).testOutput { output => output.last shouldBe 0 }
  }

  test("Can run a program that tests if the input is less than 8 using position mode") {
    val isEqualTo8 = IntcodeComputer(IntcodeState(Vector(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)))

    isEqualTo8.testInput(6).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 0 }
    isEqualTo8.testInput(10).testOutput { output => output.last shouldBe 0 }
  }

  test("Can run a program that tests if the input is equal to 8 using immediate mode") {
    val isEqualTo8 = IntcodeComputer(IntcodeState(Vector(3, 3, 1108, -1, 8, 3, 4, 3, 99)))

    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(10).testOutput { output => output.last shouldBe 0 }
  }

  test("Can run a program that tests if the input is less than 8 using immediate mode") {
    val isEqualTo8 = IntcodeComputer(IntcodeState(Vector(3, 3, 1107, -1, 8, 3, 4, 3, 99)))

    isEqualTo8.testInput(6).testOutput { output => output.last shouldBe 1 }
    isEqualTo8.testInput(8).testOutput { output => output.last shouldBe 0 }
    isEqualTo8.testInput(10).testOutput { output => output.last shouldBe 0 }
  }
}
