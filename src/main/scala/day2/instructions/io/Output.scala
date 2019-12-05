package day2.instructions.io

import day2.IntcodeState
import day2.instructions.Instruction

object Output extends Instruction {
  override val Opcode: Int = 4
  override val NumberOfParameters: Int = 1

  override def execute(program: IntcodeState): IntcodeState = {
    println(program.parameter(1).value)
    program.incrementInstructionPointer
  }
}
