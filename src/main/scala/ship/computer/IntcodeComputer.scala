package ship.computer

import ship.computer.internals.IntcodeState
import ship.computer.internals.instructions.Instructions

import scala.annotation.tailrec


object IntcodeComputer {
  val ProgramCompleteOpcode = 99

  @tailrec
  def run(program: IntcodeState): IntcodeState =
    if(program.isComplete) program else run(Instructions.execute(program))
}
