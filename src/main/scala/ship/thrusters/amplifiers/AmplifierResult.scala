package ship.thrusters.amplifiers


case class AmplifierResult(phaseSettings: Seq[Int] = Vector.empty, result: Int = 0) {
  def max(other: AmplifierResult): AmplifierResult =
    if(result > other.result) this else other

  lazy val phaseSetting: String = phaseSettings.mkString
}
