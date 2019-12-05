package day2

case class OpcodeInstruction(instruction: Int) {
  def opcode: Int = Math.abs(instruction % 100)

  def parameterMode(parameterNumber: Int): Int = Math.abs(instruction / Math.pow(10, parameterNumber + 1).toInt) % 10
}
