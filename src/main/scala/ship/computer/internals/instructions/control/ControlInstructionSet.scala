package ship.computer.internals.instructions.control

import ship.computer.internals.Configuration
import ship.computer.internals.instructions.Instruction


object ControlInstructionSet {
  def apply(configuration: Configuration): Seq[Instruction] = Vector(
    Halt,
    JumpIfTrue,
    JumpIfFalse,
    AdjustRelativeBase
  )
}
