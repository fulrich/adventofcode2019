package day2.instructions

import day2.IntcodeState

object Addition extends Instruction {
  override val Opcode = 1
  override val NumberOfParameters = 3

  def execute(program: IntcodeState): IntcodeState = {
    val firstParameter = program.address(program.parameter(1))
    val secondParameter = program.address(program.parameter(2))
    val resultAddress = program.parameter(3)

    program
      .set(resultAddress, firstParameter + secondParameter)
      .incrementInstructionPointer
  }
}
