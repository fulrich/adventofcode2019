package day2.instructions.io

import java.io.StringReader

import day2.IntcodeState
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class InputTest extends AnyFunSuite with Matchers {
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState.initial(3, 0, 99)
    val output = IntcodeState(Vector(55, 0, 99), 2)

    Console.withIn(new StringReader("55")) {
      Input.execute(program) shouldBe output
    }
  }
}
