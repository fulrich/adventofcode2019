package ship.computer.internals.instructions

import ship.computer.IntcodeProgram
import ship.computer.internals.Parameter


trait Instruction {
  val True: Int = 1
  val False: Int = 0

  def Opcode: Int
  def NumberOfParameters: Int

  def isExecutable(program: IntcodeProgram): Boolean = program.opcode == Opcode
  def execute(program: IntcodeProgram): IntcodeProgram

  def isFalse(parameter: Parameter): Boolean = parameter.value == False
  def isTrue(parameter: Parameter): Boolean = !isFalse(parameter)

  def withInstructionIncrement(executionFunction: => IntcodeProgram): IntcodeProgram =
    executionFunction.incrementInstructionPointer

  implicit class IntcodeStateExecutions(program: IntcodeProgram) {
    def incrementInstructionPointer: IntcodeProgram = program.moveInstructionPointer(NumberOfParameters + 1)

    lazy val parameterOne: Parameter = program.parameter(parameterNumber = 1)
    lazy val parameterTwo: Parameter = program.parameter(parameterNumber = 2)
    lazy val parameterThree: Parameter = program.parameter(parameterNumber = 3)
  }
}
