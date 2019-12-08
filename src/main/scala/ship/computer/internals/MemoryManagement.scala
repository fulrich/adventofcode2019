package ship.computer.internals

import ship.computer.IntcodeProgram


trait MemoryManagement { self: IntcodeProgram =>
  def parameter(parameterNumber: Int): Parameter = Parameter(this, parameterNumber)

  def address(position: Int): Int = memory(position)
  def set(address: Int, value: Int): IntcodeProgram = copy(memory = memory.updated(address, value))

  def set(parameter: Parameter, value: Int): IntcodeProgram = copy(memory = memory.updated(parameter.address, value))
}
