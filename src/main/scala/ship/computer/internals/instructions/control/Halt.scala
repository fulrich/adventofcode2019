package ship.computer.internals.instructions.control

import ship.computer.internals.IntcodeState
import ship.computer.internals.instructions.Instruction

object Halt extends Instruction {
  override def Opcode: Int = 99
  override def NumberOfParameters: Int = 1

  override def execute(program: IntcodeState): IntcodeState = program.copy(isComplete = true)
}
