package ship.computer.internals.instructions.control

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction


object AdjustRelativeBase extends Instruction {
  override val Opcode = 9
  override val NumberOfParameters = 1

  override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
    program.relativeBase(program.state.relativeBase + program.parameterOne.value)
  }
}
