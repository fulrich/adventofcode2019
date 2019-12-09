package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram
import ship.computer.internals.Configuration

import scala.annotation.tailrec


case class AmplifierSeries(phaseSettings: Seq[Int], controlProgram: IntcodeProgram = AmplificationProgram.controllerProgram.get) {
  val amplifiers: Seq[Amplifier] = phaseSettings.map { phaseSetting =>
    val primedProgram = controlProgram.configure(Configuration.singleInput).start().continue(phaseSetting)
    Amplifier(phaseSetting, primedProgram)
  }

  def run(input: Long = 0): AmplifierResult = AmplifierResult(
    phaseSettings = phaseSettings,
    result = amplifiers.foldLeft(input) { (output, amplifier) => amplifier.run(output).lastOutput}
  )

  @tailrec
  final def runLooped(input: Long = 0): AmplifierResult = {
    val result = run(input)

    if(amplifiers.last.isComplete) result
    else runLooped(result.result)
  }
}
