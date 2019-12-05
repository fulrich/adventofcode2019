package day2.instructions.control

import day2.IntcodeState
import day2.instructions.Instruction

object JumpIfFalse extends Instruction {
  override val Opcode: Int = 6
  override val NumberOfParameters: Int = 2

  override def execute(program: IntcodeState): IntcodeState =
    if(program.parameterOne.value == 0) program.setInstructionPointer(program.parameterTwo.value)
    else program.incrementInstructionPointer
}
