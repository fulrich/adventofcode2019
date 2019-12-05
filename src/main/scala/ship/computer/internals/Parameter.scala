package ship.computer.internals

case class Parameter(address: Int, value: Int) {
  def + (parameter: Parameter): Int = value + parameter.value
  def * (parameter: Parameter): Int = value * parameter.value

  def < (parameter: Parameter): Boolean = value < parameter.value
  def == (parameter: Parameter): Boolean = value == parameter.value
}


object Parameter {
  val UnknownParameter: Parameter = Parameter(-1, -1)


  def positionMode(program: IntcodeState, parameterNumber: Int): Parameter =
    at(program, program.address(program.instructionPointer + parameterNumber))

  def immediateMode(program: IntcodeState, parameterNumber: Int): Parameter =
    at(program, program.instructionPointer + parameterNumber)

  def at(program: IntcodeState, address: Int): Parameter = Parameter(address, program.address(address))


  def apply(program: IntcodeState, parameterNumber: Int): Parameter =
    program.opcodeInstruction.map(_.parameterMode(parameterNumber)) match {
      case Some(0) => positionMode(program, parameterNumber)
      case Some(1) => immediateMode(program, parameterNumber)
      case _ => UnknownParameter
    }
}
