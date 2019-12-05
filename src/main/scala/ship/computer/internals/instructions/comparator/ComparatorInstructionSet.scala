package ship.computer.internals.instructions.comparator

import ship.computer.internals.ComputerConfiguration
import ship.computer.internals.instructions.Instruction

object ComparatorInstructionSet {
  def apply(configuration: ComputerConfiguration): Seq[Instruction] = Vector(
    Equals,
    LessThan
  )
}
