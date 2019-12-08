package ship.computer.internals.instructions.arithmetic

import ship.computer.internals.Configuration
import ship.computer.internals.instructions.Instruction


object ArithmeticInstructionSet {
  def apply(configuration: Configuration): Seq[Instruction] = Vector(
    Addition,
    Multiplication
  )
}
