package day2

case class Parameter(address: Int, value: Int)


object Parameter {
  val UnknownParameter: Parameter = Parameter(-1, -1)


  def positionMode(program: IntcodeState, parameterNumber: Int): Parameter =
    atAddress(program, program.address(program.instructionPointer + parameterNumber))

  def immediateMode(program: IntcodeState, parameterNumber: Int): Parameter =
    atAddress(program, program.instructionPointer + parameterNumber)

  def atAddress(program: IntcodeState, address: Int): Parameter = Parameter(address, program.address(address))


  def apply(program: IntcodeState, parameterNumber: Int): Parameter =
    program.opcodeInstruction.map(_.parameterMode(parameterNumber)) match {
      case Some(0) => positionMode(program, parameterNumber)
      case Some(1) => immediateMode(program, parameterNumber)
      case _ => UnknownParameter
    }
}
