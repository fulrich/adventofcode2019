package asteroidbelt.targeting

import asteroidbelt.{Asteroid, AsteroidMap}


class GiantLaserPrioritization(station: Asteroid) {
  def prioritize(map: AsteroidMap): TargetList = {
    val targets = map.asteroids.filter(_ != station).map(asteroid => Target(asteroid, asteroid.slopeFrom(station)))
    val targetPaths = targets.groupBy(_.slope).values.toVector.map { targetPath =>
      targetPath.sortBy(_.asteroid.distanceTo(station)).zipWithIndex.map { case (target, index) =>
        target.copy(rotation = target.rotation + index)
      }
    }

    TargetList(targetPaths.flatten.sortBy { target =>
      (target.rotation, target.slope.quadrant.prioritizationValue, target.slope.angle)
    })
  }
}
