package ship.computer.internals.instructions.control

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction


object AdjustRelativeBase extends Instruction {
  override val Opcode: Int = 9
  override val NumberOfParameters: Int = 1

  override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
    program.relativeBase(program.parameterOne.value)
  }
}
