package asteroidbelt


case class Asteroid(x: Int, y: Int) {
  def slopeFrom(other: Asteroid): Slope = Slope(
    calculateAngle(other),
    Quadrant(other, this)
  )

  def distanceTo(other: Asteroid): Int =
    Math.abs((Math.abs(x) - Math.abs(other.x)) + (Math.abs(y) - Math.abs(other.y)))

  private def calculateAngle(other: Asteroid): Option[Double] = {
    val rise: Double = other.y - y
    val run: Double = other.x - x

    if(run == 0) None
    else Some(rise / run)
  }
}
