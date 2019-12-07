package ship.computer.internals.instructions.io

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.ComputerTesting
import ship.computer.internals.IntcodeState
import ship.computer.internals.instructions.io.OutputSource.ListOutputSource


class OutputTest extends AnyFunSuite with Matchers with ComputerTesting {
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState(4, 2, 99)
    val expected = IntcodeState(Vector(4, 2, 99), 2)

    val output = new ListOutputSource()
    Output(output).execute(program) shouldBe expected

    output.output().last shouldBe 99
  }
}
