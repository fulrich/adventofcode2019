package ship.computer.internals.instructions.arithmetic

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction


object Addition extends Instruction {
  override val Opcode = 1
  override val NumberOfParameters = 3

  def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
    program
      .set(
        program.parameterThree,
        program.parameterOne.value + program.parameterTwo.value
      )
  }
}
