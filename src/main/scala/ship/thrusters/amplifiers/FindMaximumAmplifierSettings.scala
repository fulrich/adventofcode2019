package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram

import scala.annotation.tailrec


object FindMaximumAmplifierSettings {
  val DefaultNumberOfAmplifiers = 5;
  val DefaultProgram: IntcodeProgram = AmplificationProgram.controllerProgram.get

  val MinimumPhaseSetting = 0
  val MaximumPhaseSetting = 4
  val ValidValues: Seq[Int] = (MinimumPhaseSetting to MaximumPhaseSetting).toVector

  def apply(controlProgram: IntcodeProgram = DefaultProgram, numberOfAmplifiers: Int = DefaultNumberOfAmplifiers): AmplifierResult =
    possibleAmplifierSettings(numberOfAmplifiers).foldLeft(AmplifierResult()) { (result, amplifierSettings) =>
      result.max(AmplifierSeries(amplifierSettings, controlProgram).run())
    }

  @tailrec
  def possibleAmplifierSettings(numberOfAmplifiers: Int, settings: Seq[Seq[Int]] = Vector.empty): Seq[Seq[Int]] = {
    val newSettings = ValidValues.flatMap { validValue =>
      settings match {
        case Seq() => Vector(Vector(validValue))
        case _ => settings.flatMap { previousSettings =>
          if(previousSettings.contains(validValue)) None else Some(validValue +: previousSettings)
        }
      }
    }

    if(numberOfAmplifiers == 1) newSettings
    else possibleAmplifierSettings(numberOfAmplifiers - 1, newSettings)
  }
}
