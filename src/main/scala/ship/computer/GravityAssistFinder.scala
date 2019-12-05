package ship.computer

import ship.computer.internals.IntcodeState

import scala.annotation.tailrec

object GravityAssistFinder {
  val NounRange: Range = 0 to 99
  val VerbRange: Range = 0 to 99

  val SearchingFor = 19690720
  val GravityAssistProgram: IntcodeState = Programs.gravityAssist.get

  @tailrec
  def find(noun: Int = 0, verb: Int = 0): Int = {
    val computer = IntcodeComputer(GravityAssistProgram.set(1, noun).set(2, verb))
    val result = computer.execute()

    (noun, verb, result.address(0)) match {
      case (_, _, SearchingFor) => (100 * noun + verb)
      case (99, 99, _) => -1
      case (nounValue, _, _) if nounValue < 99 => find(noun + 1, verb)
      case (99, verbValue, _) if verbValue < 99 => find(0, verb +1)
    }
  }
}
