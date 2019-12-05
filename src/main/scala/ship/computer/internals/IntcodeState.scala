package ship.computer.internals

case class IntcodeState(memory: Seq[Int], instructionPointer: Int = 0, isComplete: Boolean = false) {
  def moveInstructionPointer(increment: Int): IntcodeState = setInstructionPointer(instructionPointer + increment)
  def setInstructionPointer(address: Int): IntcodeState = copy(instructionPointer = address)

  lazy val opcodeInstruction: Option[Instruction] = memory.lift(instructionPointer).map(Instruction)
  lazy val instruction: Option[Int] = opcodeInstruction.map(_.opcode);

  def parameter(parameterNumber: Int): Parameter = Parameter(this, parameterNumber)

  def address(position: Int): Int = memory(position)
  def set(address: Int, value: Int): IntcodeState = copy(memory = memory.updated(address, value))

  def set(parameter: Parameter, value: Int): IntcodeState = copy(memory = memory.updated(parameter.address, value))
}

object IntcodeState {
  def apply(intcodes: Int*): IntcodeState = IntcodeState(intcodes.toVector, 0)
}
