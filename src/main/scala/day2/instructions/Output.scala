package day2.instructions
import day2.IntcodeState

object Output extends Instruction {
  override val Opcode: Int = 4
  override val NumberOfParameters: Int = 1

  override def execute(program: IntcodeState): IntcodeState = {
    println(program.parameter(1).value)
    program.incrementInstructionPointer
  }
}
