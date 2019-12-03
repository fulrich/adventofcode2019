package day2


case class IntcodeState(memory: Seq[Int], instructionPointer: Int = 0, isComplete: Boolean = false) {
  def moveInstructionPointer(increment: Int): IntcodeState = copy(instructionPointer = instructionPointer + increment)
  def instruction: Option[Int] = memory.lift(instructionPointer);

  def parameter(parameterNumber: Int): Int = address(instructionPointer + parameterNumber)

  def address(position: Int): Int = memory(position)
  def set(position: Int, value: Int): IntcodeState = copy(memory = memory.updated(position, value))
}

object IntcodeState {
  def initial(intcodes: Int*): IntcodeState = IntcodeState(intcodes.toVector, 0)
}
