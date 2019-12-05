package ship.computer.internals.instructions.io

import java.io.ByteArrayOutputStream

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.IntcodeState

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
