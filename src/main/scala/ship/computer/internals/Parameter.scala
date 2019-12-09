package ship.computer.internals

import ship.computer.IntcodeProgram

case class Parameter(address: Long, value: Long) {
  def + (parameter: Parameter): Long = value + parameter.value
  def * (parameter: Parameter): Long = value * parameter.value

  def < (parameter: Parameter): Boolean = value < parameter.value
  def == (parameter: Parameter): Boolean = value == parameter.value
}


object Parameter {
  val UnknownParameter: Parameter = Parameter(-1, -1)


  def positionMode(program: IntcodeProgram, parameterNumber: Long): Parameter =
    at(program, program.address(program.instructionPointer + parameterNumber))

  def immediateMode(program: IntcodeProgram, parameterNumber: Long): Parameter =
    at(program, program.instructionPointer + parameterNumber)

  def relativeMode(program: IntcodeProgram, parameterNumber: Long): Parameter =
    at(program, program.address(program.instructionPointer + parameterNumber) + program.state.relativeBase)

  def at(program: IntcodeProgram, address: Long): Parameter = Parameter(address, program.address(address))


  def apply(program: IntcodeProgram, parameterNumber: Long): Parameter =
    program.parameterMode(parameterNumber) match {
      case 0 => positionMode(program, parameterNumber)
      case 1 => immediateMode(program, parameterNumber)
      case 2 => relativeMode(program, parameterNumber)
      case _ => UnknownParameter
    }
}
