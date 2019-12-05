package ship.computer

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.IntcodeState

class IntcodeComputerTest extends AnyFunSuite with Matchers with ComputerTesting {
  test("Can handle simple addition intcode programs") {
    val program = IntcodeState.initial(1, 0, 0, 0, 99)
    val output = Vector(2, 0, 0, 0, 99)

    IntcodeComputer.run(program).memory shouldBe output
  }

  test("Can handle simple multiplication intcode programs") {
    val program = IntcodeState.initial(2, 3, 0, 3, 99)
    val output = Vector(2, 3, 0, 6, 99)

    IntcodeComputer.run(program).memory shouldBe output
  }

  test("Can handle complex multiplication intcode programs") {
    val program = IntcodeState.initial(2, 4, 4, 5, 99, 0)
    val output = Vector(2, 4, 4, 5, 99, 9801)

    IntcodeComputer.run(program).memory shouldBe output
  }

  test("Can handle complex intcode programs") {
    val program = IntcodeState.initial(1, 1, 1, 4, 99, 5, 6, 0, 99)
    val output = Vector(30, 1, 1, 4, 2, 5, 6, 0, 99)

    IntcodeComputer.run(program).memory shouldBe output
  }

  test("Can handle different modes of parameters") {
    val program = IntcodeState.initial(1002, 4, 3, 4, 33)
    val output = Vector(1002, 4, 3, 4, 99)

    IntcodeComputer.run(program).memory shouldBe output
  }

  test("Can run an input and output program") {
    val program = IntcodeState.initial(3, 0, 4, 0, 99)
    val programCompleteState = Vector(250, 0, 4, 0, 99)

    val output = withIO("250") {
      IntcodeComputer.run(program).memory shouldBe programCompleteState
    }

    output.head shouldBe 250
  }

  test("Can run a program that tests if the input is equal to 8 using position mode") {
    val isEqualTo8 = IntcodeState(Vector(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8))

    val runWith8Result = withIO("8") { IntcodeComputer.run(isEqualTo8) }
    val runWith10Result = withIO("10") { IntcodeComputer.run(isEqualTo8) }

    runWith8Result.head shouldBe 1
    runWith10Result.head shouldBe 0
  }

  test("Can run a program that tests if the input is less than 8 using position mode") {
    val isEqualTo8 = IntcodeState(Vector(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8))

    val runWith6Result = withIO("6") { IntcodeComputer.run(isEqualTo8) }
    val runWith8Result = withIO("8") { IntcodeComputer.run(isEqualTo8) }
    val runWith10Result = withIO("10") { IntcodeComputer.run(isEqualTo8) }

    runWith6Result.head shouldBe 1
    runWith8Result.head shouldBe 0
    runWith10Result.head shouldBe 0
  }

  test("Can run a program that tests if the input is equal to 8 using immediate mode") {
    val isEqualTo8 = IntcodeState(Vector(3, 3, 1108, -1, 8, 3, 4, 3, 99))

    val runWith8Result = withIO("8") { IntcodeComputer.run(isEqualTo8) }
    val runWith10Result = withIO("10") { IntcodeComputer.run(isEqualTo8) }

    runWith8Result.head shouldBe 1
    runWith10Result.head shouldBe 0
  }

  test("Can run a program that tests if the input is less than 8 using immediate mode") {
    val isEqualTo8 = IntcodeState(Vector(3, 3, 1107, -1, 8, 3, 4, 3, 99))

    val runWith6Result = withIO("6") { IntcodeComputer.run(isEqualTo8) }
    val runWith8Result = withIO("8") { IntcodeComputer.run(isEqualTo8) }
    val runWith10Result = withIO("10") { IntcodeComputer.run(isEqualTo8) }

    runWith6Result.head shouldBe 1
    runWith8Result.head shouldBe 0
    runWith10Result.head shouldBe 0
  }
}
