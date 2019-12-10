package ship.thrusters.amplifiers

import ship.computer.{IntcodeProgram, Programs}


object FindMaximumAmplifierSettings {
  val DefaultNumberOfAmplifiers = 5;
  val DefaultProgram: IntcodeProgram = Programs.ThrusterAmplification.get

  val MinimumPhaseSetting = 0
  val MaximumPhaseSetting = 4
  val ValidValues: Seq[Int] = (MinimumPhaseSetting to MaximumPhaseSetting).toVector

  val MinimumLoopedPhaseSetting = 5
  val MaximumLoopedPhaseSetting = 9
  val ValidLoopedValues: Seq[Int] = (MinimumLoopedPhaseSetting to MaximumLoopedPhaseSetting).toVector


  def apply(controlProgram: IntcodeProgram = DefaultProgram): AmplifierSeries =
    find(ValidValues) { possibleAmplifierSetting =>
      AmplifierSeries(possibleAmplifierSetting, controlProgram).run()
    }

  def looped(controlProgram: IntcodeProgram = DefaultProgram): AmplifierSeries =
    find(ValidLoopedValues) { possibleAmplifierSetting =>
      AmplifierSeries(possibleAmplifierSetting, controlProgram).runLooped()
    }


  private def find(validAmplifierSettings: Seq[Int])(amplifier: Seq[Int] => AmplifierSeries): AmplifierSeries = {
    val possibleAmplifierSettings = validAmplifierSettings.permutations.toVector
    val firstSeries = amplifier(possibleAmplifierSettings.head)

    possibleAmplifierSettings.foldLeft(firstSeries) { (result, possibleAmplifierSetting) =>
      result.max(amplifier(possibleAmplifierSetting))
    }
  }
}
