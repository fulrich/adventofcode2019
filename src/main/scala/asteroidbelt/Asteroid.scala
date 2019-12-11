package asteroidbelt

import grids.PointLike


case class Asteroid(x: Int, y: Int) extends PointLike {
  def slopeFrom(other: Asteroid): Slope = Slope(
    calculateAngle(other),
    Quadrant(other, this)
  )

  private def calculateAngle(other: Asteroid): Option[Double] = {
    val rise: Double = other.y - y
    val run: Double = other.x - x

    if(run == 0) None
    else Some(rise / run)
  }
}
