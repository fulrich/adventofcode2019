package day2.instructions

import day2.IntcodeState
import day2.instructions.control.Halt

object Instructions {
  val All: Seq[Instruction] = Vector(
    control.Halt,
    control.JumpIfTrue,
    control.JumpIfFalse,
    comparator.Equals,
    comparator.LessThan,
    arithmetic.Addition,
    arithmetic.Multiplication,
    io.Input,
    io.Output
  )

  def execute(program: IntcodeState): IntcodeState =
    All.find(_.isExecutable(program)) match {
      case Some(instruction) =>instruction.execute(program)
      case _ => Halt.execute(program)
    }
}
