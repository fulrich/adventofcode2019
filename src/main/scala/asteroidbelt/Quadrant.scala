package asteroidbelt


sealed trait Quadrant {
  def prioritizationValue: Int
}

object Quadrant {
  case object QuadrantOne extends Quadrant { override val prioritizationValue = 1 }
  case object QuadrantTwo extends Quadrant { override val prioritizationValue = 2 }
  case object QuadrantThree extends Quadrant { override val prioritizationValue = 3 }
  case object QuadrantFour extends Quadrant { override val prioritizationValue = 4 }
  case object UnknownQuadrant extends Quadrant { override val prioritizationValue = 99 }

  def apply(reference: Asteroid, point: Asteroid): Quadrant =
    if(point.x >= reference.x && point.y <= reference.y) QuadrantOne
    else if (point.x >= reference.x && point.y > reference.y) QuadrantTwo
    else if (point.x < reference.x && point.y > reference.y) QuadrantThree
    else if (point.x < reference.x && point.y <= reference.y) QuadrantFour
    else UnknownQuadrant
}
