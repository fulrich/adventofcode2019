package ship.computer.internals.instructions.arithmetic

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction


object Multiplication extends Instruction {
  override val Opcode = 2
  override val NumberOfParameters = 3


  def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
    program
      .set(program.parameter(3), program.parameter(1).value * program.parameter(2).value)
  }
}
