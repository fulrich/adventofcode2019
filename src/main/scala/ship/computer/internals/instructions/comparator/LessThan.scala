package ship.computer.internals.instructions.comparator

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction


object LessThan extends Instruction {
  override val Opcode = 7
  override val NumberOfParameters = 3

  override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
    if (program.parameterOne < program.parameterTwo) program.set(program.parameterThree, True)
    else program.set(program.parameterThree, False)
  }
}
