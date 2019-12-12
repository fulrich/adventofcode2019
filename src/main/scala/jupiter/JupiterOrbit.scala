package jupiter


case class JupiterOrbit(moons: Seq[Moon]) {
  lazy val totalEnergy: Int = moons.map(_.totalEnergy).sum
  def orbitAfter(steps: Int): JupiterOrbit = GravitySimulator.step(this, steps)
  def stepsToInitial: Long = GravitySimulator.stepsToInitial(GravitySimulator.simulate(this), 1)

  lazy val noVelocity: Boolean = moons.forall(_.noVelocity)
}

object JupiterOrbit {
  val Scan: JupiterOrbit = JupiterOrbit(Vector(
    Moon.at(5, y=13, z = -3),
    Moon.at(18, y = -7, z=13),
    Moon.at(16, y=3, z=4),
    Moon.at(0, y=8, z=8)
  ))
}
