package day2.instructions.arithmetic

import day2.IntcodeState
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class AdditionTest extends AnyFunSuite with Matchers {
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState.initial(1, 0, 0, 0, 99)
    val output = IntcodeState(Vector(2, 0, 0, 0, 99), 4)

    Addition.execute(program) shouldBe output
  }
}
