package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram
import ship.computer.internals.Configuration


case class Amplifier(phaseSetting: Int, amplificationProgram: IntcodeProgram) {
  def run(input: Int = 0): Int = {
    val configuration = Configuration.static(Vector(phaseSetting, input))

    amplificationProgram.configure(configuration).execute()
    configuration.output.trackedOutput.last
  }
}
