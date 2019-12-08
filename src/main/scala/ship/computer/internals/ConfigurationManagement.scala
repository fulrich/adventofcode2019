package ship.computer.internals

import ship.computer.IntcodeProgram


trait ConfigurationManagement { self: IntcodeProgram =>
  def configure(update: Configuration => Configuration): IntcodeProgram = copy(configuration = update(configuration))
}
