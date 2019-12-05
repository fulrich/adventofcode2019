package ship.computer.internals.instructions.io

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.ComputerTesting
import ship.computer.internals.IntcodeState


class InputTest extends AnyFunSuite with Matchers with ComputerTesting{
  test("Adds the first two parameters and outputs it to the third parameter address") {
    val program = IntcodeState(3, 0, 99)
    val output = IntcodeState(Vector(55, 0, 99), 2)

    val inputSource = new TestInputSource(Vector(55))
    Input(inputSource).execute(program) shouldBe output
  }
}
