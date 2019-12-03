package day2

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class IntcodeComputerTest extends AnyFunSuite with Matchers {
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
}
