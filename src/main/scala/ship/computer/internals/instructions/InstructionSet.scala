package ship.computer.internals.instructions

import ship.computer.IntcodeProgram
import ship.computer.internals.Configuration
import ship.computer.internals.instructions.arithmetic.ArithmeticInstructionSet
import ship.computer.internals.instructions.comparator.ComparatorInstructionSet
import ship.computer.internals.instructions.control.{ControlInstructionSet, Halt}
import ship.computer.internals.instructions.io.IoInstructionSet


case class InstructionSet(configuration: Configuration) {
  lazy val all: Seq[Instruction] =
    ArithmeticInstructionSet(configuration) ++
    ComparatorInstructionSet(configuration) ++
    ControlInstructionSet(configuration) ++
    IoInstructionSet(configuration)

  def execute(program: IntcodeProgram): IntcodeProgram =
    all.find(_.isExecutable(program)) match {
      case Some(instruction) => instruction.execute(program)
      case _ => Halt.execute(program)
    }
}
