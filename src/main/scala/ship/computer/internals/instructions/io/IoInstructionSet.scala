package ship.computer.internals.instructions.io

import ship.computer.internals.Configuration

object IoInstructionSet {
  def apply(configuration: Configuration) = Vector(
    configuration.input,
    configuration.output
  )
}
