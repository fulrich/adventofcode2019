package ship.computer.internals.instructions

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class InstructionsTest extends AnyFunSuite with Matchers {
  test("There should be no duplicate instruction opcodes") {
    Instructions.All.map(_.Opcode).distinct should contain theSameElementsAs Instructions.All.map(_.Opcode)
  }
}
