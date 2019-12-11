package asteroidbelt


case class Slope(angle: Option[Double], quadrant: Quadrant)

object Slope {
  def apply(angle: Double, quadrant: Quadrant): Slope = Slope(Some(angle), quadrant)
}
