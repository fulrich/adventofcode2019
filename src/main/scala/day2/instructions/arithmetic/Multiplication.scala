package day2.instructions.arithmetic

import day2.IntcodeState
import day2.instructions.Instruction

object Multiplication extends Instruction {
  override val Opcode = 2
  override val NumberOfParameters = 3

  def execute(program: IntcodeState): IntcodeState =
    program
      .set(program.parameter(3), program.parameter(1).value * program.parameter(2).value)
      .incrementInstructionPointer
}
