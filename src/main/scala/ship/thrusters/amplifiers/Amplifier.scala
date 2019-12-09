package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram


case class Amplifier(phaseSetting: Int, var amplificationProgram: IntcodeProgram) {
  def lastOutput: Long = amplificationProgram.configuration.output.trackedOutput.last
  def isComplete: Boolean = amplificationProgram.state.isComplete

  def run(input: Long = 0): Amplifier = {
    amplificationProgram = amplificationProgram.continue(input)
    this
  }
}
