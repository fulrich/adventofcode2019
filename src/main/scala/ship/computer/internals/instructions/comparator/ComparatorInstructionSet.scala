package ship.computer.internals.instructions.comparator

import ship.computer.internals.Configuration
import ship.computer.internals.instructions.Instruction

object ComparatorInstructionSet {
  def apply(configuration: Configuration): Seq[Instruction] = Vector(
    Equals,
    LessThan
  )
}
