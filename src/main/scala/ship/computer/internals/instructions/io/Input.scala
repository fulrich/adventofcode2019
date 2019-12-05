package ship.computer.internals.instructions.io

import ship.computer.internals.IntcodeState
import ship.computer.internals.instructions.Instruction

import scala.io.StdIn

case class Input(source: InputSource) extends Instruction {
  override val Opcode: Int = 3
  override val NumberOfParameters: Int = 1

  override def execute(program: IntcodeState): IntcodeState =
    program
      .set(program.parameter(1), source.get())
      .incrementInstructionPointer
}
