package ship.computer.internals.instructions.arithmetic

import ship.computer.internals.ComputerConfiguration
import ship.computer.internals.instructions.Instruction


object ArithmeticInstructionSet {
  def apply(configuration: ComputerConfiguration): Seq[Instruction] = Vector(
    Addition,
    Multiplication
  )
}
