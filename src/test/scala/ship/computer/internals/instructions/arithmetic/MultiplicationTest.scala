package ship.computer.internals.instructions.arithmetic

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.IntcodeState

class MultiplicationTest extends AnyFunSuite with Matchers {
  test("Multiplies the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState.initial(2, 3, 0, 3, 99)
    val output = IntcodeState(Vector(2, 3, 0, 6, 99), instructionPointer = 4)

    Multiplication.execute(program) shouldBe output
  }
}
