package ship.computer.internals

import ship.computer.IntcodeProgram


trait InstructionControl { self: IntcodeProgram =>
  private lazy val instructionPointerValue: Long = address(instructionPointer)

  def opcode: Long = Math.abs(instructionPointerValue % 100)
  def parameterMode(parameterNumber: Long): Long = Math.abs(instructionPointerValue / Math.pow(10, parameterNumber + 1).toInt) % 10

  def moveInstructionPointer(increment: Long): IntcodeProgram = setInstructionPointer(instructionPointer + increment)
  def setInstructionPointer(address: Long): IntcodeProgram = copy(instructionPointer = address)
}
