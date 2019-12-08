package ship.computer.internals.instructions.io

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction


case class Input(source: InputSource) extends Instruction {
  override val Opcode: Int = 3
  override val NumberOfParameters: Int = 1

  override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
    program
      .set(program.parameter(1), source.get())
  }
}
