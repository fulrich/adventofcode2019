package jupiter

import scala.annotation.tailrec


object GravitySimulator {
  @tailrec
  final def step(jupiter: JupiterOrbit, steps: Int = 1): JupiterOrbit =
    if(steps == 0) jupiter
    else step(simulate(jupiter), steps- 1)

  @tailrec
  def stepsToInitial(jupiter: JupiterOrbit, count: Long = 0): Long = {
    if(jupiter.noVelocity) count * 2
    else  stepsToInitial(simulate(jupiter), count + 1)
  }

  def simulate(jupiter: JupiterOrbit): JupiterOrbit = JupiterOrbit(updateVelocities(jupiter).map(_.applyVelocity))

  private def updateVelocities(jupiterOrbit: JupiterOrbit): Seq[Moon] =
    jupiterOrbit.moons.map { moon =>
      jupiterOrbit.moons.filter(_ != moon).foldLeft(moon) { case (updatedMoon, otherMoon) =>
        updatedMoon.applyGravity(otherMoon)
      }
    }
}

