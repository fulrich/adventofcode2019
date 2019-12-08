package ship.computer.internals.instructions

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.Configuration


class InstructionSetTest extends AnyFunSuite with Matchers {
  test("There should be no duplicate instruction opcodes") {
    val allInstructionCodes = InstructionSet(Configuration.Default).all.map(_.Opcode)
    val allDistinctInstructionCodes = InstructionSet(Configuration.Default).all.map(_.Opcode).distinct

    allInstructionCodes should contain theSameElementsAs allDistinctInstructionCodes
  }
}
