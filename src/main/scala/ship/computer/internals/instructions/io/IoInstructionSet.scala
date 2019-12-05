package ship.computer.internals.instructions.io

import ship.computer.internals.ComputerConfiguration

object IoInstructionSet {
  def apply(configuration: ComputerConfiguration) = Vector(
    Input(configuration.inputSource),
    Output(configuration.outputSource)
  )
}
