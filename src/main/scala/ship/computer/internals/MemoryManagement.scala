package ship.computer.internals

import ship.computer.IntcodeProgram


trait MemoryManagement { self: IntcodeProgram =>
  def parameter(parameterNumber: Long): Parameter = Parameter(this, parameterNumber)

  def address(position: Long): Long = memory.lift(position.toInt).getOrElse(0)
  def set(address: Long, value: Long): IntcodeProgram = copy(memory = memory.updated(address.toInt, value))

  def set(parameter: Parameter, value: Long): IntcodeProgram = {
    copy(memory = memory.padTo(parameter.address.toInt + 1, 0L).updated(parameter.address.toInt, value))
  }
}
