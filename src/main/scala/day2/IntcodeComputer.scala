package day2

import day2.instructions.Instructions

import scala.annotation.tailrec


object IntcodeComputer {
  val ProgramCompleteOpcode = 99

  @tailrec
  def run(program: IntcodeState): IntcodeState =
    if(program.isComplete) program else run(Instructions.execute(program))
}
