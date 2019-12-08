package ship.computer.internals.instructions.io

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction


case class Output(source: OutputSource) extends Instruction {
  override val Opcode: Int = 4
  override val NumberOfParameters: Int = 1

  override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
    source.output(program.parameter(1).value)
    program
  }
}
