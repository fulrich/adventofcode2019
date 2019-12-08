package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram


case class Amplifier(phaseSetting: Int, var amplificationProgram: IntcodeProgram) {
  def lastOutput: Int = amplificationProgram.configuration.output.trackedOutput.last
  def isComplete: Boolean = amplificationProgram.state.isComplete

  def run(input: Int = 0): Amplifier = {
    amplificationProgram = amplificationProgram.continue(input)
    this
  }
}
