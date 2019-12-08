package ship.computer.internals

import ship.computer.IntcodeProgram

case class Parameter(address: Int, value: Int) {
  def + (parameter: Parameter): Int = value + parameter.value
  def * (parameter: Parameter): Int = value * parameter.value

  def < (parameter: Parameter): Boolean = value < parameter.value
  def == (parameter: Parameter): Boolean = value == parameter.value
}


object Parameter {
  val UnknownParameter: Parameter = Parameter(-1, -1)


  def positionMode(program: IntcodeProgram, parameterNumber: Int): Parameter =
    at(program, program.address(program.instructionPointer + parameterNumber))

  def immediateMode(program: IntcodeProgram, parameterNumber: Int): Parameter =
    at(program, program.instructionPointer + parameterNumber)

  def at(program: MemoryManagement, address: Int): Parameter = Parameter(address, program.address(address))


  def apply(program: IntcodeProgram, parameterNumber: Int): Parameter =
    program.parameterMode(parameterNumber) match {
      case 0 => positionMode(program, parameterNumber)
      case 1 => immediateMode(program, parameterNumber)
      case _ => UnknownParameter
    }
}
