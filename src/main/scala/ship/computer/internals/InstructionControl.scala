package ship.computer.internals

import ship.computer.IntcodeProgram


trait InstructionControl { self: IntcodeProgram =>
  private lazy val instructionPointerValue: Int = address(instructionPointer)

  def opcode: Int = Math.abs(instructionPointerValue % 100)
  def parameterMode(parameterNumber: Int): Int = Math.abs(instructionPointerValue / Math.pow(10, parameterNumber + 1).toInt) % 10

  def moveInstructionPointer(increment: Int): IntcodeProgram = setInstructionPointer(instructionPointer + increment)
  def setInstructionPointer(address: Int): IntcodeProgram = copy(instructionPointer = address)
}
