package ship.computer.internals.instructions.control

import ship.computer.internals.ComputerConfiguration
import ship.computer.internals.instructions.Instruction

object ControlInstructionSet {
  def apply(configuration: ComputerConfiguration): Seq[Instruction] = Vector(
    Halt,
    JumpIfTrue,
    JumpIfFalse,
  )
}
