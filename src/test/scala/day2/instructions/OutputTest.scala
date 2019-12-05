package day2.instructions

import java.io.ByteArrayOutputStream

import day2.IntcodeState
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class OutputTest extends AnyFunSuite with Matchers {
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState.initial(4, 2, 99)
    val output = IntcodeState(Vector(4, 2, 99), 2)

    val out = new ByteArrayOutputStream()
    Console.withOut(out) {
      Output.execute(program) shouldBe output
    }

    out.toString.trim shouldBe "99"
  }
}
