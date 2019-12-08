package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram


case class AmplifierSeries(phaseSettings: Seq[Int], controlProgram: IntcodeProgram = AmplificationProgram.controllerProgram.get) {
  val amplifiers: Seq[Amplifier] = phaseSettings.map(phaseSetting => Amplifier(phaseSetting, controlProgram))

  def run(): AmplifierResult = AmplifierResult(
    phaseSettings = phaseSettings,
    result = amplifiers.foldLeft(0) { (output, amplifier) => amplifier.run(output) }
  )
}
