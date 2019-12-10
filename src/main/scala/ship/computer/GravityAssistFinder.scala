package ship.computer

import scala.annotation.tailrec

object GravityAssistFinder {
  val NounRange: Range = 0 to 99
  val VerbRange: Range = 0 to 99

  val SearchingFor = 19690720
  val GravityAssistProgram: IntcodeProgram = Programs.GravityAssist.get

  @tailrec
  def find(noun: Int = 0, verb: Int = 0): Int = {
    val computer = GravityAssistProgram.set(1, noun).set(2, verb)
    val result = computer.start()

    (noun, verb, result.address(0)) match {
      case (_, _, SearchingFor) => (100 * noun + verb)
      case (99, 99, _) => -1
      case (nounValue, _, _) if nounValue < 99 => find(noun + 1, verb)
      case (99, verbValue, _) if verbValue < 99 => find(0, verb +1)
    }
  }
}
