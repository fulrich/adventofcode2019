package ship.computer.internals

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.io.{Input, Output}


trait ConfigurationManagement { self: IntcodeProgram =>
  def configure(update: Configuration => Configuration): IntcodeProgram = copy(configuration = update(configuration))
  def configure(configuration: Configuration): IntcodeProgram = copy(configuration = configuration)

  def setInput(newInput: Input): IntcodeProgram = configure(_.copy(input = newInput))
  def setOutput(newOutput: Output): IntcodeProgram = configure(_.copy(output = newOutput))
}
