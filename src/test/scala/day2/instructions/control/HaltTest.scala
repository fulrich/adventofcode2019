package day2.instructions.control

import day2.IntcodeState
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class HaltTest extends AnyFunSuite with Matchers {
  test("The Halt instruction should set the incode state to complete") {
    val program = IntcodeState.initial(2, 3, 0, 3, 99)

    Halt.execute(program).isComplete shouldBe true
  }
}
