package ship.computer.internals.instructions

import ship.computer.internals.IntcodeState
import ship.computer.internals.instructions.control.Halt

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
