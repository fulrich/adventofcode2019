package day2

import java.io.StringReader


case class IntcodeState(memory: Seq[Int], instructionPointer: Int = 0, isComplete: Boolean = false) {
  def moveInstructionPointer(increment: Int): IntcodeState = copy(instructionPointer = instructionPointer + increment)
  lazy val opcodeInstruction: Option[OpcodeInstruction] = memory.lift(instructionPointer).map(OpcodeInstruction)
  lazy val instruction: Option[Int] = opcodeInstruction.map(_.opcode);

  def parameter(parameterNumber: Int): Parameter = Parameter(this, parameterNumber)

  def address(position: Int): Int = memory(position)
  def set(parameter: Parameter, value: Int): IntcodeState = copy(memory = memory.updated(parameter.address, value))
  def set(address: Int, value: Int): IntcodeState = copy(memory = memory.updated(address, value))

  new StringReader("")
}

object IntcodeState {
  def initial(intcodes: Int*): IntcodeState = IntcodeState(intcodes.toVector, 0)
}
