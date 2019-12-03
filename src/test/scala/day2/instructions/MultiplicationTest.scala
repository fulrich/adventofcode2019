package day2.instructions

import day2.IntcodeState
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MultiplicationTest extends AnyFunSuite with Matchers {
  test("Multiplies the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState.initial(2, 3, 0, 3, 99)
    val output = IntcodeState(Vector(2, 3, 0, 6, 99), instructionPointer = 4)

    Multiplication.execute(program) shouldBe output
  }
}