package day2.instructions

import day2.IntcodeState

object Instructions {
  val All: Seq[Instruction] = Vector(
    Addition,
    Multiplication,
    Halt
  )

  def execute(program: IntcodeState): IntcodeState =
    All.find(_.isExecutable(program)) match {
      case Some(instruction) =>instruction.execute(program)
      case _ => program
    }
}
