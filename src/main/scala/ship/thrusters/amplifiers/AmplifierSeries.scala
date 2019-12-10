package ship.thrusters.amplifiers

import ship.computer.{IntcodeProgram, Programs}

import scala.annotation.tailrec


case class AmplifierSeries(phaseSettings: Seq[Int], amplifiers: Seq[Amplifier]) {
  lazy val lastOutput: Long = amplifiers.lastOption.map(_.lastOutput).getOrElse(0)
  def max(other: AmplifierSeries): AmplifierSeries = if(lastOutput > other.lastOutput) this else other

  private def +(amplifier: Amplifier): AmplifierSeries = copy(amplifiers = amplifiers :+ amplifier)
  private def nextRun: AmplifierSeries = copy(amplifiers = amplifiers.tail)
  private def runHead(input: Long): Amplifier = amplifiers.head.run(input)


  final def run(input: Long = 0, series: AmplifierSeries = this): AmplifierSeries =
    run(input, series, AmplifierSeries(series.phaseSettings, Vector.empty))

  @tailrec
  final def run(input: Long, series: AmplifierSeries, completedSeries: AmplifierSeries): AmplifierSeries =
    if(series.amplifiers.isEmpty) completedSeries
    else {
      val nextCompletedAmplifier = series.runHead(input)
      run(nextCompletedAmplifier.lastOutput, series.nextRun, completedSeries + nextCompletedAmplifier)
    }

  @tailrec
  final def runLooped(input: Long = 0, series: AmplifierSeries = this): AmplifierSeries = {
    val updatedSeries = run(input, series)

    if(updatedSeries.amplifiers.last.isComplete) updatedSeries
    else runLooped(updatedSeries.lastOutput, updatedSeries)
  }
}

object AmplifierSeries {
  val DefaultControlProgram: IntcodeProgram = Programs.ThrusterAmplification.get

  def apply(phaseSettings: Seq[Int], controlProgram: IntcodeProgram = DefaultControlProgram): AmplifierSeries = {
    val amplifiers: Seq[Amplifier] = phaseSettings.map { phaseSetting =>
      Amplifier.primedPhaseSetting(phaseSetting, controlProgram)
    }

    AmplifierSeries(phaseSettings, amplifiers)
  }
}
