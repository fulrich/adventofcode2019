package day2.instructions

import day2.IntcodeState

trait Instruction {
  def Opcode: Int
  def NumberOfParameters: Int

  def execute(program: IntcodeState): IntcodeState

  def isExecutable(program: IntcodeState): Boolean = program.instruction.contains(Opcode)

  implicit class IntcodeStateExecutions(program: IntcodeState) {
    def incrementInstructionPointer: IntcodeState = program.moveInstructionPointer(NumberOfParameters + 1)
  }
}
