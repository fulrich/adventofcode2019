package ship.computer.internals.instructions.comparator

import ship.computer.internals.IntcodeState
import ship.computer.internals.instructions.Instruction


object LessThan extends Instruction {
  override val Opcode: Int = 7
  override val NumberOfParameters: Int = 3

  override def execute(program: IntcodeState): IntcodeState = withInstructionIncrement {
    if (program.parameterOne < program.parameterTwo) program.set(program.parameterThree, True)
    else program.set(program.parameterThree, False)
  }
}
