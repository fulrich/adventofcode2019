package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram
import ship.computer.internals.Configuration


case class Amplifier(phaseSetting: Long, amplificationProgram: IntcodeProgram) {
  def lastOutput: Long = amplificationProgram.configuration.output.trackedOutput.lastOption.getOrElse(0)
  def isComplete: Boolean = amplificationProgram.state.isComplete

  def run(input: Long = 0): Amplifier = copy(amplificationProgram = amplificationProgram.continue(input))
}

object Amplifier {
  def primedPhaseSetting(phaseSetting: Long, program: IntcodeProgram): Amplifier = Amplifier(
    phaseSetting = phaseSetting,
    amplificationProgram = program.
      configure(Configuration.singleInput)
      .start()
      .continue(phaseSetting)
  )
}
