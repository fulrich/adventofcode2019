package asteroidbelt

import scala.io.Source
import scala.util.Try


case class AsteroidMap(asteroids: Seq[Asteroid]) {
  def asteroidWithMostVisibleAsteroids: Asteroid = asteroids.maxBy(visibleAsteroidsFor)

  def visibleAsteroidsFor(asteroid: Asteroid): Int =
    if(asteroids.contains(asteroid)) asteroids.filter(_ != asteroid).map(_.slopeFrom(asteroid)).distinct.length else 0
}

object AsteroidMap {
  val CeresStationMap = "asteroidbelt/asteroid_map.txt"

  def fromLines(asteroids: Seq[String]): AsteroidMap = AsteroidMap(
    asteroids.zipWithIndex.flatMap { case (asteroidString, y) =>
      asteroidString.toVector.zipWithIndex.collect {
        case (asteroidChar, x) if asteroidChar == '#' => Asteroid(x, y)
      }
    }
  )

  def Ceres: Try[AsteroidMap] = Try(Source.fromResource(CeresStationMap).getLines.toVector).map(fromLines)
}
