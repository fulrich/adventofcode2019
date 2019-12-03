package day2.instructions
import day2.IntcodeState

object Halt extends Instruction {
  override def Opcode: Int = 99
  override def NumberOfParameters: Int = 1

  override def execute(program: IntcodeState): IntcodeState = program.copy(isComplete = true)
}
