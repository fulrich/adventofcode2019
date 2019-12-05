package ship.computer.internals.instructions.io

import ship.computer.internals.IntcodeState
import ship.computer.internals.instructions.Instruction

object Output extends Instruction {
  override val Opcode: Int = 4
  override val NumberOfParameters: Int = 1

  override def execute(program: IntcodeState): IntcodeState = {
    println(program.parameter(1).value)
    program.incrementInstructionPointer
  }
}
