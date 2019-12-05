package day2.instructions

import day2.{IntcodeState, Parameter}

trait Instruction {
  val True: Int = 1
  val False: Int = 0

  def Opcode: Int
  def NumberOfParameters: Int

  def isExecutable(program: IntcodeState): Boolean = program.instruction.contains(Opcode)
  def execute(program: IntcodeState): IntcodeState

  def isFalse(parameter: Parameter): Boolean = parameter.value == False
  def isTrue(parameter: Parameter): Boolean = !isFalse(parameter)

  def withInstructionIncrement(executionFunction: => IntcodeState): IntcodeState =
    executionFunction.incrementInstructionPointer

  implicit class IntcodeStateExecutions(program: IntcodeState) {
    def incrementInstructionPointer: IntcodeState = program.moveInstructionPointer(NumberOfParameters + 1)

    lazy val parameterOne: Parameter = program.parameter(parameterNumber = 1)
    lazy val parameterTwo: Parameter = program.parameter(parameterNumber = 2)
    lazy val parameterThree: Parameter = program.parameter(parameterNumber = 3)
  }
}
