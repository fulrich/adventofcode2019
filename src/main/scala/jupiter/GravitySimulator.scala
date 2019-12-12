package jupiter

import scala.annotation.tailrec


object GravitySimulator {
  @tailrec
  final def step(jupiter: JupiterOrbit, steps: Int = 1): JupiterOrbit =
    if(steps == 0) jupiter
    else {
      val updatedOrbit = JupiterOrbit(updateVelocities(jupiter).map(_.applyVelocity))
      step(updatedOrbit, steps- 1)
    }

  private def updateVelocities(jupiterOrbit: JupiterOrbit): Seq[Moon] =
    jupiterOrbit.moons.map { moon =>
      jupiterOrbit.moons.filter(_ != moon).foldLeft(moon) { case (updatedMoon, otherMoon) =>
        updatedMoon.applyGravity(otherMoon)
      }
    }
}

