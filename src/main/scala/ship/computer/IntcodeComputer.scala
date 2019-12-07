package ship.computer

import ship.computer.internals.instructions.InstructionSet
import ship.computer.internals.{ComputerConfiguration, IntcodeState}

import scala.annotation.tailrec


case class IntcodeComputer(initial: IntcodeState, configuration: ComputerConfiguration = ComputerConfiguration.Default) {
  private lazy val instructionSet: InstructionSet = InstructionSet(configuration)

  @tailrec
  final def execute(program: IntcodeState = initial): IntcodeState =
    if(program.isComplete) program
    else execute(instructionSet.execute(program))

  def configure(configuration: ComputerConfiguration): IntcodeComputer = copy(configuration = configuration)
}

object IntcodeComputer {
  def apply(intcodes: Int*): IntcodeComputer = IntcodeComputer(IntcodeState(intcodes))
}