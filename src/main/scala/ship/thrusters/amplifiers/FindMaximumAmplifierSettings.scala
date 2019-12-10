package ship.thrusters.amplifiers

import ship.computer.{IntcodeProgram, Programs}


object FindMaximumAmplifierSettings {
  val DefaultProgram: IntcodeProgram = Programs.ThrusterAmplification.get

  val ValidPhaseValues: Seq[Seq[Int]] = (0 to 4).toVector.permutations.toVector
  val ValidLoopedPhaseValues: Seq[Seq[Int]] = (5 to 9).toVector.permutations.toVector


  def apply(controlProgram: IntcodeProgram = DefaultProgram): AmplifierSeries =
    find(ValidPhaseValues) { possibleAmplifierSetting =>
      AmplifierSeries(possibleAmplifierSetting, controlProgram).run()
    }

  def looped(controlProgram: IntcodeProgram = DefaultProgram): AmplifierSeries =
    find(ValidLoopedPhaseValues) { possibleAmplifierSetting =>
      AmplifierSeries(possibleAmplifierSetting, controlProgram).runLooped()
    }


  private def find(validAmplifierSettings: Seq[Seq[Int]])(amplifier: Seq[Int] => AmplifierSeries): AmplifierSeries = {
    val firstSeries = amplifier(validAmplifierSettings.head)

    validAmplifierSettings.foldLeft(firstSeries) { (result, possibleAmplifierSetting) =>
      result.max(amplifier(possibleAmplifierSetting))
    }
  }
}
