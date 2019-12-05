package day2.instructions.comparator

import day2.IntcodeState
import day2.instructions.Instruction


object Equals extends Instruction {
  override val Opcode: Int = 8
  override val NumberOfParameters: Int = 3

  override def execute(program: IntcodeState): IntcodeState = withInstructionIncrement {
    if (program.parameterOne == program.parameterTwo) program.set(program.parameterThree, True)
    else program.set(program.parameterThree, False)
  }
}
