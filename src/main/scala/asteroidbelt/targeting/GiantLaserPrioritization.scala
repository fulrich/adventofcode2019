package asteroidbelt.targeting

import asteroidbelt.{Asteroid, AsteroidMap}


class GiantLaserPrioritization(station: Asteroid) {
  val TargetOrder: Ordering[Target] = Ordering.by { target: Target =>
    (target.rotation, target.slope.quadrant.prioritizationValue, target.slope.angle)
  }

  def prioritize(map: AsteroidMap): TargetList = {
    val targets = map.asteroids.filter(_ != station).map(toTarget)
    val alignedTargets = targets.groupBy(_.slope).values.toVector
    val targetsWithRotation = alignedTargets.flatMap(addRotationToTargets)

    TargetList(targetsWithRotation.sorted(TargetOrder))
  }

  private def toTarget(asteroid: Asteroid): Target = Target(asteroid, asteroid.slopeFrom(station))

  private def addRotationToTargets(targets: Seq[Target]): Seq[Target] =
    targets.sortBy(_.asteroid.distanceTo(station)).zipWithIndex.map { case (target, index) =>
      target.copy(rotation = target.rotation + index)
    }
}
